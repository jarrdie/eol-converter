package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.string.StringTool.isEmpty;
import static jarrdie.eolconverter.tool.string.StringTool.splitLiteral;
import java.nio.file.*;

public class FileTool {

    public static boolean exists(String path) {
        Path directoryPath = Paths.get(path);
        return Files.exists(directoryPath);
    }

    public static String getFileName(String path) {
        if (isEmpty(path)) {
            return "";
        }
        path = path.replace("\\", "/");
        if (path.endsWith("/")) {
            return "";
        }
        String[] parts = splitLiteral(path, "/");
        return parts[parts.length - 1];
    }

}
