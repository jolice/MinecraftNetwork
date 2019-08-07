package me.riguron.config.factory.stream;

import me.riguron.io.stream.StreamWriter;

public interface StreamWriterFactory {

    StreamWriter newStreamWriter(String dst);
}
