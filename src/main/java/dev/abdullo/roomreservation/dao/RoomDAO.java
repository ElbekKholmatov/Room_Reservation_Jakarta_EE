package dev.abdullo.roomreservation.dao;

import dev.abdullo.roomreservation.domains.Field;
import dev.abdullo.roomreservation.domains.Room;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDAO extends BaseDAO<Room, Long> {
    private static final ThreadLocal<RoomDAO> ROOM_DAO_THREAD_LOCAL = ThreadLocal.withInitial(RoomDAO::new);

    public static RoomDAO getInstance() {
        return ROOM_DAO_THREAD_LOCAL.get();
    }

    public Map<Room, Integer> getAvailableRoomsAndSeats(Field field, LocalDate reservationDate) {
        Map<Room, Integer> availableRoomsAndSeats = new HashMap<>();
        List<Room> roomsByField = getRoomsByField(field);
        roomsByField.forEach(room -> availableRoomsAndSeats.put(room, room.getCapacity()));
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            for (Room room : roomsByField) {
                int reservedSeats = em.createQuery("select count(r) from Reservation r where r.room = :room and r.reservationDate = :reservationDate and not isExpired", Long.class)
                        .setParameter("room", room)
                        .setParameter("reservationDate", reservationDate)
                        .getSingleResult().intValue();
                availableRoomsAndSeats.put(room, room.getCapacity() - reservedSeats);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableRoomsAndSeats;
    }

    public List<Room> getRoomsByField(Field field) {
        List<Room> rooms = null;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            rooms = em.createQuery("select r from Room r where r.field = :field", Room.class)
                    .setParameter("field", field)
                    .getResultList();
            em.getTransaction().commit();
        }
        return rooms;
    }

    public List<String> getReservedSeatsOnDate(LocalDate reservationDate, Room room) {
        List<Integer> reservedSeats = null;
        List<String> seats = new ArrayList<>();
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservedSeats = em.createQuery("select r.seatNumber from Reservation r where r.room = :room and r.reservationDate = :reservationDate and not isExpired", Integer.class)
                    .setParameter("room", room)
                    .setParameter("reservationDate", reservationDate)
                    .getResultList();
            em.getTransaction().commit();
        }
        reservedSeats.forEach(e -> seats.add(e.toString()));
        return seats;
    }
}
