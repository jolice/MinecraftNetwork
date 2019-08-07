package me.riguron.io.provider.url;

import lombok.RequiredArgsConstructor;
import me.riguron.io.provider.OutputStreamProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.URL;

@RequiredArgsConstructor
public class URLOutputStreamProvider implements OutputStreamProvider {

    private final URL url;

    @Override
    public OutputStream getOutputStream() {
        try {
            return url.openConnection().getOutputStream();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
