package me.riguron.config.json;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import me.riguron.io.provider.OutputStreamProvider;
import me.riguron.io.stream.StreamWriter;

import java.io.*;

@AllArgsConstructor
public class JsonStreamWriter implements StreamWriter {

    private Gson gson;
    private OutputStreamProvider outputStreamProvider;

    @Override
    public void write(Object target) {
        try (OutputStream out = outputStreamProvider.getOutputStream()) {
            try (Writer writer = new OutputStreamWriter(out, "UTF-8")) {
                gson.toJson(out, writer);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
