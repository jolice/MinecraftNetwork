package com.github.jolice.system.internalization;

import java.util.*;
import java.util.function.Supplier;

/**
 * Internalization enables players to see server messages in a language they specify.
 */
public class InternalizationService {

    /**
     * Map holding message translations for each locale.
     */
    private Map<Locale, Map<String, String>> messages = new HashMap<>();

    /**
     * Some locales are used rarely, so it makes sense not to hold some of them in memory,
     * and delay the loading until the specified locale is requested for the first time. By default,
     * each locale data is not held in memory, instead, the loading is deferred until the first request.
     * It allows for saving some memory.
     */
    private Map<Locale, Supplier<Map<String, String>>> delayedLocales = new HashMap<>();

    /**
     * Return the localized version of the specified message.
     *
     * @param localizable object holding the locale
     * @param message     object containing key and arguments of localized message
     * @return version of source message in a specified locale, or raw source message
     * key if there is no localized version of the provided message.
     */
    public String getMessage(Localizable localizable, Message message) {
        try {
            return String.format(this.getMessage(localizable.getLocale(), message.getKey()), message.getArguments());
        } catch (IllegalFormatException formatException) {
            throw new InvalidMessageFormatException(formatException);
        }

    }

    private String getMessage(Locale locale, String key) {
        return messages.compute(locale, (l, keys) -> {
            if (keys == null) {
                keys = delayedLocales.getOrDefault(l, Collections::emptyMap).get();
            }
            return keys;
        }).getOrDefault(key, key);
    }

    /**
     * Adds deferred provider of messages for specified locale.
     *
     * @param locale           locale of the messages
     * @param messagesSupplier lazy provider of internalized messages
     */
    public void add(Locale locale, Supplier<Map<String, String>> messagesSupplier) {
        this.delayedLocales.put(locale, messagesSupplier);
    }

}
