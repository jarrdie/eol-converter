package jarrdie.eolconverter.tool.encoding;

public interface Encoding {

    public String getName();

    public byte[] getBom();

    public byte[] getCr();

    public byte[] getLf();

    public byte[] getCrLf();

}
