package jarrdie.eolconverter.tool.encoding;

public class UnknownEncoding implements Encoding {

    @Override
    public String getName() {
        return "Unknown";
    }

    @Override
    public byte[] getBom() {
        return new byte[0];
    }

    @Override
    public byte[] getCr() {
        return new byte[0];
    }

    @Override
    public byte[] getLf() {
        return new byte[0];
    }

    @Override
    public byte[] getCrLf() {
        return new byte[0];
    }

}
