package io.riguron.config.factory.stream;

import com.google.gson.Gson;
import io.riguron.config.json.JsonStreamReader;
import lombok.RequiredArgsConstructor;
import io.riguron.io.factory.stream.StreamProviderFactory;
import io.riguron.io.stream.StreamReader;

@RequiredArgsConstructor
public class JsonStreamReaderFactory implements StreamReaderFactory {

    private final Gson gson;
    private final StreamProviderFactory streamProviderFactory;

    @Override
    public <T> StreamReader<T> newStreamReader(String target, Class<T> type) {
        return new JsonStreamReader<>(gson, type, streamProviderFactory.newInputStreamProvider(target));
    }
}
