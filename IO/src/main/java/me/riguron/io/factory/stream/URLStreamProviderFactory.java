package me.riguron.io.factory.stream;

import lombok.RequiredArgsConstructor;
import me.riguron.io.factory.DataFormat;
import me.riguron.io.provider.InputStreamProvider;
import me.riguron.io.provider.OutputStreamProvider;
import me.riguron.io.provider.url.URLInputStreamProvider;
import me.riguron.io.provider.url.URLOutputStreamProvider;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

@RequiredArgsConstructor
public class URLStreamProviderFactory implements StreamProviderFactory {

    private final DataFormat dataFormat;
    private final String urlPath;

    @Override
    public InputStreamProvider newInputStreamProvider(String source) {
        return newProvider(URLInputStreamProvider::new, source);
    }

    @Override
    public OutputStreamProvider newOutputStreamProvider(String target) {
        return newProvider(URLOutputStreamProvider::new, target);
    }

    private <T> T newProvider(Function<URL, T> function, String target) {
        return function.apply(makeUrl(target));
    }

    private URL makeUrl(String address) {
        try {
            return new URL(urlPath + "/" + dataFormat.append(address));
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
    }
}
