package dev.abdullo.roomreservation.services;

import dev.abdullo.roomreservation.dao.FieldDAO;

public class FieldService {
    private static final ThreadLocal<FieldService> FIELD_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(FieldService::new);

    public static FieldService getInstance() {
        return FIELD_SERVICE_THREAD_LOCAL.get();
    }


    public boolean doesExist(String fieldId) {
        return FieldDAO.getInstance().findById(Long.valueOf(fieldId)) != null;
    }
}
