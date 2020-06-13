package io.riguron.io.provider.url;

import lombok.RequiredArgsConstructor;
import io.riguron.io.provider.InputStreamProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;

@RequiredArgsConstructor
public class URLInputStreamProvider implements InputStreamProvider {

    private final URL url;

    @Override
    public InputStream getInputStream() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
