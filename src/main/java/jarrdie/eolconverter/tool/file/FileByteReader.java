package jarrdie.eolconverter.tool.file;

import java.io.*;

public class FileByteReader {

    public static InputStream open(String path) throws Exception {
        if (path == null) {
            throw new Exception("Error trying to open an empty file path");
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

    public static void close(InputStream input) throws Exception {
        if (input == null) {
            return;
        }
        input.close();
    }
}
