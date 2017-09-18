package jarrdie.eolconverter.tool.file;

import java.nio.file.*;

public class FileTool {

    public static boolean exists(String path) {
        Path directoryPath = Paths.get(path);
        return Files.exists(directoryPath);
    }

}
