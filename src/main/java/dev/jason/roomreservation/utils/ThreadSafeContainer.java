package dev.jason.roomreservation.utils;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface ThreadSafeContainer {
    ConcurrentMap<Long, LocalDate> reservationDates = new ConcurrentHashMap<>();
}
