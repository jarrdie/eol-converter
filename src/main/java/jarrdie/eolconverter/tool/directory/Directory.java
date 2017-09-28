package jarrdie.eolconverter.tool.directory;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import jarrdie.eolconverter.tool.file.*;
import static jarrdie.eolconverter.tool.string.StringTool.*;
import java.io.*;
import java.nio.file.*;
import static java.util.Comparator.*;

public class Directory {

    public static String generateTemporalPath(String directory) {
        String path = TMP_DIR;
        if (exists(PROJECT_BUILD_PATH)) {
            path = PROJECT_BUILD_PATH;
        }
        if (!endsWithFileSeparator(path)) {
            path += FS;
        }
        return path + directory + FS;
    }

    public static boolean endsWithFileSeparator(String path) {
        if (isEmpty(path)) {
            return false;
        }
        return path.endsWith("/") || path.endsWith("\\");
    }

    public static boolean exists(String path) {
        return FileTool.exists(path);
    }

    public static void regenerateDirectory(String path) throws Exception {
        removeDirectory(path);
        createDirectory(path);
    }

    public static void createDirectory(String path) throws Exception {
        if (exists(path)) {
            return;
        }
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
                .sorted(reverseOrder())
                .forEach(File::delete);
    }

}
