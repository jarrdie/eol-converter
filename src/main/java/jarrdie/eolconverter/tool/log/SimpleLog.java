package jarrdie.eolconverter.tool.log;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import java.util.logging.*;

public class SimpleLog {

    private static final Logger log = Logger.getLogger(SimpleLog.class.getName());

    public static void info(String message) {
        log.info(message);
    }

    public static void info(byte[] bytes) {
        String message = convertToHexadecimalMatrix(bytes);
        log.info(message);
    }

}
