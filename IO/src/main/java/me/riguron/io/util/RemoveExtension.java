package me.riguron.io.util;

import java.util.function.Function;

public class RemoveExtension implements Function<String, String> {

    @Override
    public String apply(String value) {
        return value.contains(".") ? value.substring(0, value.lastIndexOf('.')) : value;
    }
}
