package dev.jason.roomreservation.dao;

import dev.jason.roomreservation.domains.Field;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldDAO extends BaseDAO<Field, Long> {
    private static final ThreadLocal<FieldDAO> FIELD_DAO_THREAD_LOCAL = ThreadLocal.withInitial(FieldDAO::new);

    public static FieldDAO getInstance() {
        return FIELD_DAO_THREAD_LOCAL.get();
    }
}
