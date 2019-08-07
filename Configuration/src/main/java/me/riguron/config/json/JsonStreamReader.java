package me.riguron.config.json;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import me.riguron.io.stream.StreamReader;
import me.riguron.io.provider.InputStreamProvider;

import java.io.*;

@AllArgsConstructor
public class JsonStreamReader<T> implements StreamReader<T> {

    private Gson gson;
    private Class<T> type;
    private InputStreamProvider inputStreamProvider;

    @Override
    public T read() {
        try (InputStream inputStream = inputStreamProvider.getInputStream()) {
            try (Reader reader = new InputStreamReader(inputStream)) {
                return gson.fromJson(reader, type);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
