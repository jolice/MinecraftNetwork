package me.riguron.io.file;

import lombok.RequiredArgsConstructor;
import me.riguron.io.provider.InputStreamProvider;

import java.io.InputStream;

@RequiredArgsConstructor
public class ResourceInputStreamProvider implements InputStreamProvider {

    private final String resource;

    @Override
    public InputStream getInputStream() {
        final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resource);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resource);
        }
        return resourceAsStream;
    }
}
