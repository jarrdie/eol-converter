package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;

public class Utf32Le implements Encoding {

    @Override
    public String getName() {
        return "UTF-32LE";
    }

    @Override
    public byte[] getBom() {
        return convertFromHexadecimal("FF FE 00 00");
    }

    @Override
    public byte[] getCr() {
        return convertFromHexadecimal("0D 00 00 00");
    }

    @Override
    public byte[] getLf() {
        return convertFromHexadecimal("0A 00 00 00");
    }

    @Override
    public byte[] getCrLf() {
        return convertFromHexadecimal("0D 00 00 00 0A 00 00 00");
    }

}
