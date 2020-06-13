package com.github.jolice.system.internalization;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a localized message. It consists of the key - the string that
 * has an associated localized value in the properties file, and arguments -
 * any variable values in the localized message. For example, given Message, where
 * <p>
 * key is: "category.hello.world"
 * and arguments is: "%s"
 * <p>
 * then appropriate localized message in properties file (en.properties) may look like:
 * category.hello.world=Hello world, %s!"
 */
@Getter
@EqualsAndHashCode
@ToString
public class Message {

    /**
     * Key of the localized message in the corresponding translation file. Desired format is: x.y.z
     */
    private final String key;

    /**
     * Variable values in the message.
     */
    private final Object[] arguments;

    public Message(String key, Object... arguments) {
        this.key = key;
        this.arguments = arguments;
    }
}
