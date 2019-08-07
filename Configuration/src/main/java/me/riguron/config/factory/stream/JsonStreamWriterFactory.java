package me.riguron.config.factory.stream;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import me.riguron.config.json.JsonStreamWriter;
import me.riguron.io.factory.stream.StreamProviderFactory;
import me.riguron.io.stream.StreamWriter;

@RequiredArgsConstructor
public class JsonStreamWriterFactory implements StreamWriterFactory {

    private final StreamProviderFactory streamProviderFactory;
    private final Gson gson;

    @Override
    public StreamWriter newStreamWriter(String dst) {
        return new JsonStreamWriter(gson, streamProviderFactory.newOutputStreamProvider(dst));
    }
}
