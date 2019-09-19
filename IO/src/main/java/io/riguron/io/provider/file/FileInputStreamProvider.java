package io.riguron.io.provider.file;

import lombok.AllArgsConstructor;
import io.riguron.io.provider.InputStreamProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class FileInputStreamProvider implements InputStreamProvider {

    private Path path;

    @Override
    public InputStream getInputStream() {
        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
