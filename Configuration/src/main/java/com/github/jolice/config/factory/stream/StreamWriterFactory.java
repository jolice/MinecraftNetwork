package io.riguron.config.factory.stream;

import io.riguron.io.stream.StreamWriter;

public interface StreamWriterFactory {

    StreamWriter newStreamWriter(String dst);
}
