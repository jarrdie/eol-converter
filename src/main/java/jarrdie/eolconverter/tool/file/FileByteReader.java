package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.string.StringTool.isEmpty;
import java.io.*;

public class FileByteReader {

    public static InputStream openInput(String path) throws Exception {
        if (isEmpty(path)) {
            throw new Exception("Error trying to open for reading an empty file path");
        }
        InputStream input = FileByteReader.class.getResourceAsStream(path);
        if (input == null) {
            input = new FileInputStream(path);
        }
        return input;
    }

    public static int read(InputStream input, byte[] buffer) throws Exception {
        if (input == null || buffer == null || buffer.length == 0) {
            return 0;
        }
        int numberOfBytesRead = input.read(buffer);
        return numberOfBytesRead;
    }

    public static boolean hasNext(InputStream input) throws Exception {
        return input.available() > 0;
    }

    public static void close(InputStream input) throws Exception {
        if (input == null) {
            return;
        }
        input.close();
    }

}
