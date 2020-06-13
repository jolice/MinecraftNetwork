package io.riguron.config.factory.stream;

import io.riguron.io.stream.StreamReader;

public interface StreamReaderFactory {

    <T> StreamReader<T> newStreamReader(String target, Class<T> type);
}
