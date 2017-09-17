package jarrdie.eolconverter.util;

import java.io.*;
import static java.io.File.separator;
import static java.lang.System.getProperty;
import java.nio.file.*;

public class Directory {

    public static void createTemporalDirectory(String directory) throws Exception {
        String directoryPath = getProperty("java.io.tmpdir") + separator + directory;
        createDirectory(directoryPath);
    }

    public static void createDirectory(String directoryPath) throws Exception {
        Path path = Paths.get(directoryPath);
        Files.createDirectory(path);
    }

    public static void removeDirectory(String path) throws Exception {
        Files.walk(Paths.get(path))
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }

}
