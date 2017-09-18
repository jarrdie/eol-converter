package jarrdie.eolconverter.tool.converter;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import java.util.*;

public class NumericConverter {

    private static final char[] hexadecimalDigits = "0123456789ABCDEF".toCharArray();

    public static String convertToHexadecimal(byte[] buffer, int length) {
        byte[] bytes = Arrays.copyOf(buffer, length);
        return convertToHexadecimal(bytes);
    }

    public static String convertToHexadecimal(byte[] bytes) {
        return convertToHexadecimalMatrix(bytes).trim();
    }

    public static String convertToHexadecimalMatrix(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        int rowLength = 10;
        List<String> hexadecimalData = convertToHexadecimalList(bytes);
        String data = "";
        for (int i = 0; i < hexadecimalData.size(); i++) {
            if (i % rowLength == 0) {
                data += EOL;
            }
            data += hexadecimalData.get(i);
            if (i != (hexadecimalData.size() - 1)) {
                data += " ";
            }
        }
        return data;
    }

    public static List<String> convertToHexadecimalList(byte[] bytes) {
        if (bytes == null) {
            return new ArrayList();
        }
        List<String> data = new ArrayList();
        char[] characters = new char[2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            characters[0] = hexadecimalDigits[v >>> 4];
            characters[1] = hexadecimalDigits[v & 0x0F];
            data.add(new String(characters));
        }
        return data;
    }

}
