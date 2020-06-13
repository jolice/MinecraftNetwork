package io.riguron.io.list;

import io.riguron.io.util.RemoveExtension;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericDirectoryList implements DirectoryList {

    @Override
    public List<String> listDirectory(String directory) {
        return getFiles(directory)
                .stream()
                .map(new RemoveExtension())
                .collect(Collectors.toList());
    }

    abstract List<String> getFiles(String directory);


}
