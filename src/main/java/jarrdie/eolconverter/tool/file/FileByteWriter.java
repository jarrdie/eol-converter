package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.string.StringTool.isEmpty;
import java.io.*;

public class FileByteWriter {

    public static OutputStream openOutput(String path) throws Exception {
        if (isEmpty(path)) {
            throw new Exception("Error trying to open for writing an empty file path");
        }
        OutputStream output = new FileOutputStream(path);
        return output;
    }

    public static void write(OutputStream output, byte[] bytes) throws Exception {
        if (output == null || bytes == null || bytes.length == 0) {
            return;
        }
        output.write(bytes);
    }

    public static void close(OutputStream output) throws Exception {
        if (output == null) {
            return;
        }
        output.close();
    }

}
