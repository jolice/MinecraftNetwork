package io.riguron.io.list;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDirectoryList extends GenericDirectoryList {

    @Override
    List<String> getFiles(String directory) {
        try (Stream<Path> files = Files.list(Paths.get(directory))) {
            return files.map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
