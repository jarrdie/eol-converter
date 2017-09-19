package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;

public class Utf16le implements Encoding {

    @Override
    public String getName() {
        return "UTF-16LE";
    }

    @Override
    public byte[] getBom() {
        return convertFromHexadecimal("FF FE");
    }

    @Override
    public byte[] getCr() {
        return convertFromHexadecimal("0D 00");
    }

    @Override
    public byte[] getLf() {
        return convertFromHexadecimal("0A 00");
    }

    @Override
    public byte[] getCrLf() {
        return convertFromHexadecimal("0D 00 0A 00");
    }

}
