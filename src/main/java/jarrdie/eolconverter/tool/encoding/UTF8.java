package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;

public class UTF8 implements Encoding {

    @Override
    public String getName() {
        return "UTF-8";
    }

    @Override
    public byte[] getBom() {
        return convertFromHexadecimal("EF BB BF");
    }

    @Override
    public byte[] getCr() {
        return convertFromHexadecimal("0D");
    }

    @Override
    public byte[] getLf() {
        return convertFromHexadecimal("0A");
    }

    @Override
    public byte[] getCrLf() {
        return convertFromHexadecimal("0D 0A");
    }

}
