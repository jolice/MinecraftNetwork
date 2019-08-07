package me.riguron.config.factory.stream;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import me.riguron.config.json.JsonStreamReader;
import me.riguron.io.factory.stream.StreamProviderFactory;
import me.riguron.io.stream.StreamReader;

@RequiredArgsConstructor
public class JsonStreamReaderFactory implements StreamReaderFactory {

    private final Gson gson;
    private final StreamProviderFactory streamProviderFactory;

    @Override
    public <T> StreamReader<T> newStreamReader(String target, Class<T> type) {
        return new JsonStreamReader<>(gson, type, streamProviderFactory.newInputStreamProvider(target));
    }
}
