package me.riguron.io.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DataFormat {

    JSON("json"), YAML("yaml");

    @Getter
    private final String extension;

    public String append(String fileName) {
        return fileName + "." + extension;
    }

}
