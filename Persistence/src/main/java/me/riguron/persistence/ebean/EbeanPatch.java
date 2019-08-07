package me.riguron.persistence.ebean;

import java.lang.reflect.Field;

public class EbeanPatch {

    private EbeanPatch() {
    }

    /**
     * Workaround that just makes Ebean work. Should be fixed.
     */
    public static void apply() {
        try {
            final Field skip = Class.forName("io.ebean.PrimaryServer").getDeclaredField("skip");
            skip.setAccessible(true);
            skip.set(null, true);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
