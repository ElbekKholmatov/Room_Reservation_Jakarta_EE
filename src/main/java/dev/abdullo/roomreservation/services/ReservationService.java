package dev.abdullo.roomreservation.services;

import dev.abdullo.roomreservation.dao.ReservationDAO;

public class ReservationService {
    private static final ThreadLocal<ReservationService> RESERVATION_DAO_THREAD_LOCAL = ThreadLocal.withInitial(ReservationService::new);

    public boolean changeDeleted(Long userId) {

        //todo check userId  is valid
        boolean result = ReservationDAO.getInstance().changeDeleted(userId);
        return result;
    }

    public static ReservationService getInstance() {
        return RESERVATION_DAO_THREAD_LOCAL.get();
    }
}
