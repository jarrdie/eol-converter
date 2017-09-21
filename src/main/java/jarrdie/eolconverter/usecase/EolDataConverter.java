package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.startsWithAny;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.detectEncoding;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.hasBom;
import jarrdie.eolconverter.tool.encoding.*;
import static jarrdie.eolconverter.usecase.EolConversion.CR;
import static jarrdie.eolconverter.usecase.EolConversion.CRLF;
import static jarrdie.eolconverter.usecase.EolConversion.LF;

enum EolConversion {
    CR, LF, CRLF
}

public class EolDataConverter {

    private int MAXIMUN_BLOCK_LENTGH_TO_REPLACE = 4;

    private boolean isFirst;
    private EolConversion eolConversion;
    private Encoding encoding;
    private byte[] block;
    private byte[] outputData;
    private int outputLength;

    public EolDataConverter(EolConversion eolConversion) {
        this.isFirst = true;
        this.eolConversion = eolConversion;
        this.encoding = new UnknownEncoding();
        this.block = new byte[MAXIMUN_BLOCK_LENTGH_TO_REPLACE];
    }

    public void convert(byte[] data, int dataLength, byte[] outputData) {
        this.outputData = outputData;
        int inputCursor = 0;
        int outputCursor = 0;
        int blockLenght = 0;
        byte[][] eolsToFind = new byte[2][];
        byte[] finalEol = new byte[0];
        if (isFirst) {
            encoding = detectEncoding(data);
            if (eolConversion == LF) {
                finalEol = encoding.getLf();
                eolsToFind[0] = encoding.getCr();
                eolsToFind[1] = encoding.getCrLf();
            }
            if (eolConversion == CR) {
                finalEol = encoding.getCr();
                eolsToFind[0] = encoding.getLf();
                eolsToFind[1] = encoding.getCrLf();
            }
            if (eolConversion == CRLF) {
                finalEol = encoding.getCrLf();
                eolsToFind[0] = encoding.getCr();
                eolsToFind[1] = encoding.getLf();
            }
            if (hasBom(data)) {
                byte[] bom = encoding.getBom();
                for (int j = 0; j < bom.length; j++) {
                    outputData[outputCursor++] = bom[j];
                }
                inputCursor += bom.length;
            }
            blockLenght = encoding.getLf().length;
        }
        if (finalEol.length == 0) {
            return;
        }
        while (inputCursor < dataLength) {
            for (int i = 0; i < MAXIMUN_BLOCK_LENTGH_TO_REPLACE; i++) {
                block[i] = data[inputCursor + i];
            }
            if (startsWithAny(block, eolsToFind)) {
                for (int j = 0; j < finalEol.length; j++) {
                    outputData[outputCursor++] = finalEol[j];
                }
            } else {
                for (int j = 0; j < blockLenght; j++) {
                    outputData[outputCursor++] = block[j];
                }
            }
            inputCursor += blockLenght;
            isFirst = false;
        }
        outputLength = outputCursor;
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public int getOutputLength() {
        return outputLength;
    }

}
