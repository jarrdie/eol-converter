package jarrdie.eolconverter.tool.directory;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import java.io.*;
import static java.lang.System.*;
import java.nio.file.*;

public class Directory {

    public static String getProjectBuildPath(String directory) {
        return "/home/pablo/Compartido/GitHub/eol-converter/build/tmp/" + directory;
    }

    public static String getTemporalPath(String directory) {
        return getProperty("java.io.tmpdir") + EOF + directory + EOF;
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
