package jarrdie.eolconverter.tool.comparator;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static java.util.Arrays.*;

public class ByteComparator {

    public static boolean startsWithAny(byte[] bytes, byte[][] prefixes) {
        if (bytes == null || prefixes == null) {
            return false;
        }
        for (int i = 0; i < prefixes.length; i++) {
            byte[] prefix = prefixes[i];
            if (startsWith(bytes, prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean startsWith(byte[] bytes, byte[] prefix) {
        if (bytes == null || prefix == null) {
            return false;
        }
        int length = prefix.length;
        byte[] initBytes = copyOf(bytes, length);
        return areEqual(initBytes, prefix);
    }

    public static boolean areEqual(byte[] bytes1, byte[] bytes2) {
        if (bytes1 == null || bytes2 == null) {
            return false;
        }
        String data1 = convertToHexadecimal(bytes1);
        String data2 = convertToHexadecimal(bytes2);
        return data1.equals(data2);
    }

    public static void compare(byte[] bytes1, byte[] bytes2) throws Exception {
        if (bytes1 == null || bytes2 == null) {
            throw new Exception("One of the byte arrays to compare is empty");
        }
        String data1 = convertToHexadecimal(bytes1);
        String data2 = convertToHexadecimal(bytes2);
        if (!data1.equals(data2)) {
            throw new Exception("The byte array comparison found differences" + EOL
                    + "First byte array: " + convertToHexadecimal(bytes1) + EOL
                    + "Second byte array: " + convertToHexadecimal(bytes2));
        }
    }

}
