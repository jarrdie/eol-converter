package jarrdie.eolconverter.tool.converter;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import jarrdie.eolconverter.tool.string.*;
import java.util.*;
import static java.util.Arrays.*;

public class NumericConverter {

    private static final char[] hexadecimalDigits = "0123456789ABCDEF".toCharArray();

    public static String convertToHexadecimal(byte[] buffer, int length) {
        byte[] bytes = copyOf(buffer, length);
        return convertToHexadecimal(bytes);
    }

    public static String convertToHexadecimal(byte[] bytes) {
        String data = convertToHexadecimalMatrix(bytes);
        data = data.replace(EOL, " ");
        data = data.trim();
        return data;
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
            if (esFinDeFila(i, rowLength)) {
                data += EOL;
            }
            data += hexadecimalData.get(i);
            if (!esFinDeFila(i + 1, rowLength) && !esFin(i, size)) {
                data += " ";
            }
        }
        return data;
    }

    private static boolean esFinDeFila(int i, int rowLength) {
        return (i != 0) && (i % rowLength == 0);
    }

    private static boolean esFin(int i, int size) {
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

    public static byte[] convertFromHexadecimal(String data) {
        if (StringTool.isEmpty(data)) {
            return new byte[0];
        }
        data = data.replace(" ", "");
        data = data.toUpperCase();
        byte bytes[] = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i += 2) {
            String hexadecimal = "0x" + data.charAt(i) + data.charAt(i + 1);
            bytes[i / 2] = (Integer.decode(hexadecimal)).byteValue();
        }
        return bytes;
    }

}
