package io.riguron.config.factory.stream;

import com.google.gson.Gson;
import io.riguron.config.json.JsonStreamWriter;
import lombok.RequiredArgsConstructor;
import io.riguron.io.factory.stream.StreamProviderFactory;
import io.riguron.io.stream.StreamWriter;

@RequiredArgsConstructor
public class JsonStreamWriterFactory implements StreamWriterFactory {

    private final StreamProviderFactory streamProviderFactory;
    private final Gson gson;

    @Override
    public StreamWriter newStreamWriter(String dst) {
        return new JsonStreamWriter(gson, streamProviderFactory.newOutputStreamProvider(dst));
    }
}
