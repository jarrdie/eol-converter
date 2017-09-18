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
        int size = hexadecimalData.size();
        String data = "";
        for (int i = 0; i < size; i++) {
            if (_esFinDeFila(i, rowLength)) {
                data += EOL;
            }
            data += hexadecimalData.get(i);
            if (!_esFinDeFila(i + 1, rowLength) && !_esFin(i, size)) {
                data += " ";
            }
        }
        return data;
    }

    private static boolean _esFinDeFila(int i, int rowLength) {
        return (i != 0) && (i % rowLength == 0);
    }

    private static boolean _esFin(int i, int size) {
        return i == (size - 1);
    }

    public static List<String> convertToHexadecimalList(byte[] bytes) {
        if (bytes == null) {
            return new ArrayList<>();
        }
        List<String> data = new ArrayList<>();
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
