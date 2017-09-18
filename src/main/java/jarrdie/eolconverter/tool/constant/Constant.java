package jarrdie.eolconverter.tool.constant;

import static java.io.File.*;
import static java.lang.System.*;

public class Constant {

    public static String EOF = separator;
    public static String LF = "\n";
    public static String CR = "\r";
    public static String CRLF = "\r\n";
    public static String EOL = lineSeparator();

    public static String TMP_DIR = getProperty("java.io.tmpdir");

    public static String PROJECT_BUILD_PATH = "/home/pablo/Compartido/GitHub/eol-converter/build/tmp";

}
