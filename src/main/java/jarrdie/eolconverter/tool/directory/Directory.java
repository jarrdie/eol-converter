package jarrdie.eolconverter.tool.directory;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import java.io.*;
import java.nio.file.*;

public class Directory {

    public static String generateTemporalPath(String directory) {
        String path = TMP_DIR;
        if (exists(PROJECT_BUILD_PATH)) {
            path = PROJECT_BUILD_PATH;
        }
        return path + EOF + directory + EOF;
    }

    public static boolean exists(String path) {
        Path directoryPath = Paths.get(path);
        return Files.exists(directoryPath);
    }

    public static void regenerateDirectory(String path) throws Exception {
        removeDirectory(path);
        createDirectory(path);
    }

    public static void createDirectory(String path) throws Exception {
        Path directoryPath = Paths.get(path);
        Files.createDirectory(directoryPath);
    }

    public static void removeDirectory(String path) throws Exception {
        Path directoryPath = Paths.get(path);
        if (!Files.exists(directoryPath)) {
            return;
        }
        removeDirectoryRecursively(directoryPath);
    }

    private static void removeDirectoryRecursively(Path directoryPath) throws Exception {
        Files.walk(directoryPath)
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }

}
