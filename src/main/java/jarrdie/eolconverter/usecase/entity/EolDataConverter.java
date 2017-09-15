package jarrdie.eolconverter.usecase.entity;

enum EolConversion {
    CR, LF, CRLF
}

public class EolDataConverter {

    private EolConversion eolConversion;

    public EolDataConverter(EolConversion eolConversion) {
        this.eolConversion = eolConversion;
    }

    public void convert(byte[] data, int dataLength, byte[] outputData, Integer outputLength) {

    }

}
