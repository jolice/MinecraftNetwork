package me.riguron.config.properties;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import me.riguron.io.provider.InputStreamProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PropertiesLoader {

    private final InputStreamProvider inputStreamProvider;

    public Properties load() {
        try (InputStream in = inputStreamProvider.getInputStream()) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
