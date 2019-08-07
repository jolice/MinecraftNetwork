package me.riguron.io.list;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResourceDirectoryList extends GenericDirectoryList {

    @Override
    List<String> getFiles(String path) {
        try {
            URL url = getClassLoader().getResource(path);

            if (url == null) {
                return Collections.emptyList();
            }

            URI uri = url.toURI();
            if (uri.getScheme().equals("jar")) {
                try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                    Path resourcePath = fileSystem.getPath(path);
                    return
                            Files.walk(resourcePath, 1)
                                    .skip(1)
                                    .map(Path::toString)
                                    .sorted()
                                    .collect(Collectors.toList());

                }
            } else {
                File resource = new File(uri);
                return Arrays.stream(Objects.requireNonNull(resource.listFiles())).map(file -> path + "/" + file.getName()).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
