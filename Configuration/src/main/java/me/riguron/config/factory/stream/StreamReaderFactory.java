package me.riguron.config.factory.stream;

import me.riguron.io.stream.StreamReader;

public interface StreamReaderFactory {

    <T> StreamReader<T> newStreamReader(String target, Class<T> type);
}
