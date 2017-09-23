package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.isPositiveMatch;
import static jarrdie.eolconverter.tool.comparator.ByteComparator.startsWithAny;
import static jarrdie.eolconverter.tool.converter.NumericConverter.convertToHexadecimal;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.detectEncoding;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.hasBom;
import jarrdie.eolconverter.tool.encoding.*;
import static jarrdie.eolconverter.tool.log.SimpleLog.*;
import static jarrdie.eolconverter.usecase.EolConversion.CR;
import static jarrdie.eolconverter.usecase.EolConversion.CRLF;
import static jarrdie.eolconverter.usecase.EolConversion.LF;

enum EolConversion {
    CR, LF, CRLF
}

public class EolDataConverter {

    private int MAXIMUN_BLOCK_LENTGH_TO_REPLACE = 8;

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
        int blockLength = 0;
        byte[][] eolsToFind = new byte[2][];
        byte[] finalEol = new byte[0];
        if (isFirst) {
            info("New conversion started with data: " + convertToHexadecimal(data, dataLength));
            encoding = detectEncoding(data);
            if (eolConversion == LF) {
                finalEol = encoding.getLf();
                eolsToFind[0] = encoding.getCrLf(); //The order is important
                eolsToFind[1] = encoding.getCr();
            }
            if (eolConversion == CR) {
                finalEol = encoding.getCr();
                eolsToFind[0] = encoding.getCrLf();
                eolsToFind[1] = encoding.getLf();
            }
            if (eolConversion == CRLF) {
                finalEol = encoding.getCrLf();
                eolsToFind[0] = encoding.getCr();
                eolsToFind[1] = encoding.getLf();
            }
            for (int i = 0; i < eolsToFind.length; i++) {
                info("Eol to find: " + convertToHexadecimal(eolsToFind[i]));
            }
            info("Final Eol: " + convertToHexadecimal(finalEol));
            if (hasBom(data)) {
                byte[] bom = encoding.getBom();
                for (int j = 0; j < bom.length; j++) {
                    outputData[outputCursor++] = bom[j];
                }
                inputCursor += bom.length;
                info("Bom detected and written: " + convertToHexadecimal(bom));
            }
            info("Reading blocks of length: " + MAXIMUN_BLOCK_LENTGH_TO_REPLACE);
            blockLength = encoding.getLf().length;
            info("With an offset of: " + blockLength);
        }
        if (finalEol.length == 0) {
            return;
        }
        while (inputCursor < dataLength) {
            info("Input cursor at: " + inputCursor);
            for (int i = 0; i < MAXIMUN_BLOCK_LENTGH_TO_REPLACE; i++) {
                block[i] = data[inputCursor + i];
            }
            info("Block read: " + convertToHexadecimal(block));
            int matchLength = startsWithAny(block, eolsToFind);
            if (isPositiveMatch(matchLength)) {
                info("Eol found at the beginnig of the block with length: " + matchLength);
                for (int j = 0; j < finalEol.length; j++) {
                    outputData[outputCursor++] = finalEol[j];
                }
                inputCursor += matchLength;
            } else {
                for (int j = 0; j < blockLength; j++) {
                    outputData[outputCursor++] = block[j];
                }
                inputCursor += blockLength;
            }
        }
        outputLength = outputCursor;
        info("Data conversion finished with result: " + convertToHexadecimal(outputData, outputLength));
        isFirst = false;
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public int getOutputLength() {
        return outputLength;
    }

}
