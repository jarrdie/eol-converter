package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;

public class Utf32be implements Encoding {

    @Override
    public String getName() {
        return "UTF-32BE";
    }

    @Override
    public byte[] getBom() {
        return convertFromHexadecimal("00 00 FE FF");
    }

    @Override
    public byte[] getCr() {
        return convertFromHexadecimal("00 00 00 0D");
    }

    @Override
    public byte[] getLf() {
        return convertFromHexadecimal("00 00 00 0A");
    }

    @Override
    public byte[] getCrLf() {
        return convertFromHexadecimal("00 00 00 0D 00 00 00 0A");
    }

}
