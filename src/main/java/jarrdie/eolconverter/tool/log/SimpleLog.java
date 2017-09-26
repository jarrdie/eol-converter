package jarrdie.eolconverter.tool.log;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import java.util.logging.*;

public class SimpleLog {

    public static boolean enabled = true;
    private static final Logger log = Logger.getLogger(SimpleLog.class.getName());

    public static void info(String message) {
        if (!enabled) {
            return;
        }
        log.info(message);
    }

    public static void info(byte[] bytes) {
        info("", bytes, bytes.length);
    }

    public static void info(String message, byte[] bytes) {
        info(message, bytes, bytes.length);
    }

    public static void info(String message, byte[] bytes, int length) {
        if (!enabled) {
            return;
        }
        String completeMessage = message + convertToHexadecimal(bytes, length);
        log.info(completeMessage);
    }

    public static void line() {
        if (!enabled) {
            return;
        }
        log.info("--------------------------------------------------------------");
    }

}
