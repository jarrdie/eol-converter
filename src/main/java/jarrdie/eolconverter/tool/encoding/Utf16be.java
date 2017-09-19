package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;

public class Utf16be implements Encoding {

    @Override
    public String getName() {
        return "UTF-16BE";
    }

    @Override
    public byte[] getBom() {
        return convertFromHexadecimal("FE FF");
    }

    @Override
    public byte[] getCr() {
        return convertFromHexadecimal("00 0D");
    }

    @Override
    public byte[] getLf() {
        return convertFromHexadecimal("00 0A");
    }

    @Override
    public byte[] getCrLf() {
        return convertFromHexadecimal("00 0D 00 0A");
    }

}
