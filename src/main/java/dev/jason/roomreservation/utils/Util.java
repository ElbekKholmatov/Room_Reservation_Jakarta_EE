package dev.jason.roomreservation.utils;

public class Util {
    private static final ThreadLocal<Util> instance = ThreadLocal.withInitial(Util::new);

    public String capitalize(String inputString) {
        char firstLetter = inputString.charAt(0);
        char capitalFirstLetter = Character.toUpperCase(firstLetter);
        return capitalFirstLetter + inputString.substring(1);
    }

    public static Util getInstance() {
        return instance.get();
    }
}
