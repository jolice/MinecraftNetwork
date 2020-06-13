package io.riguron.io.list;

import java.util.List;

public interface DirectoryList {

    /**
     * Returns list of files in the specified directory.
     *
     * @param directory target directory
     * @return names of files residing in the target directory
     */
    List<String> listDirectory(String directory);
}
