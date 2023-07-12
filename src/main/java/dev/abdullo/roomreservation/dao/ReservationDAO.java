package dev.abdullo.roomreservation.dao;

import dev.abdullo.roomreservation.domains.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("unchecked")
public class ReservationDAO extends BaseDAO<Reservation, Long> {
    private static final ThreadLocal<ReservationDAO> RESERVATION_DAO_THREAD_LOCAL = ThreadLocal.withInitial(ReservationDAO::new);

    public static ReservationDAO getInstance() {
        return RESERVATION_DAO_THREAD_LOCAL.get();
    }

    public List<Integer> getAvailableSeats(LocalDate reservationDate, Long fieldId) {
        List<Integer> availableSeats;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Integer> query = em.createQuery("select r.seatNumber from Reservation r where r.reservationDate = :reservationDate and r.room.field.id = :fieldId", Integer.class)

                    .setParameter("reservationDate", reservationDate)
                    .setParameter("fieldId", fieldId);
            availableSeats = query.getResultList();
        }
        return availableSeats;
    }

    public List<Reservation> getActiveReservations() {
        List<Reservation> reservations;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservations = em.createQuery("select r from Reservation r where not r.isExpired").getResultList();
            em.getTransaction().commit();
        }
        return reservations;
    }

    public List<Reservation> getExpiredReservations() {
        List<Reservation> reservations;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservations = em.createQuery("select r from Reservation r where r.isExpired").getResultList();
            em.getTransaction().commit();
        }
        return reservations;
    }

//    public boolean changeDeleted(Long aLong) {
//        int i;
//        try (EntityManager em = emf.createEntityManager()){
//            em.getTransaction().begin();
//            i = em.createQuery("update Reservation set isExpired = true where id = :id")
//                    .setParameter("id", aLong)
//                    .executeUpdate();
//            em.getTransaction().commit();
//        }
//        return i > 0;
//    }

    public boolean changeDeleted(Long aLong) {
        int i;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            i = em.createQuery("delete from Reservation  where id = :id")
                    .setParameter("id", aLong)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        return i > 0;
    }


    public List<Reservation> getActiveReservationsForUser(String userId) {
        List<Reservation> reservations;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservations = em.createQuery("select r from Reservation r where r.user.id = :userId and not isExpired")
                    .setParameter("userId", userId)
                    .getResultList();
            em.getTransaction().commit();
        }
        return reservations;
    }

    public List<Reservation> getExpiredReservationsForUser(String userId) {
        List<Reservation> reservations;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservations = em.createQuery("select r from Reservation r where r.user.id = :userId and isExpired")
                    .setParameter("userId", userId)
                    .getResultList();
            em.getTransaction().commit();
        }
        return reservations;
    }

    public List<Reservation> findAllForUser(String userId) {
        List<Reservation> reservations;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            reservations = em.createQuery("select r from Reservation r where r.user.id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();
            em.getTransaction().commit();
        }
        return reservations;
    }

    public void deleteExpiredReservations() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("update Reservation r set r.isExpired = true where r.reservationDate < current_date")
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }
}
