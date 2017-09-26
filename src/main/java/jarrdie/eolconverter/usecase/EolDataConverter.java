package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.*;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.*;
import jarrdie.eolconverter.tool.encoding.*;
import static jarrdie.eolconverter.tool.encoding.EncodingDetector.*;
import static jarrdie.eolconverter.tool.log.SimpleLog.*;
import static jarrdie.eolconverter.usecase.EolConversion.*;

enum EolConversion {
    CR, LF, CRLF
}

public class EolDataConverter {

    private boolean isFirstConversion;

    private EolConversion eolConversion;
    private byte[] finalEol;

    private Encoding encoding;
    private byte[] bom;
    private byte[][] eolsToFind;

    private byte[] block;
    private int blockLength;
    private int blockMaximumLength;

    private byte[] inputData;
    private int inputLength;
    private int inputCursor;

    private byte[] outputData;
    private int outputLength;
    private int outputCursor;

    public EolDataConverter(EolConversion eolConversion) {
        this.isFirstConversion = true;
        this.eolConversion = eolConversion;
        this.encoding = new UnknownEncoding();
    }

    public void convert(byte[] data, int dataLength, byte[] outputData) {
        initConversion(data, dataLength, outputData);
        if (isFirstConversion) {
            initFirstConversion();
            detectDataEncoding();
            initEols();
            initBlock();
            processBom();
        }
        if (!isWellInitiated()) {
            return;
        }
        while (hasMoreBlocks()) {
            initBlockRead();
            readNextBlock();
            convertBlock();
        }
        finishConversion();
    }

    private void initConversion(byte[] data, int dataLength, byte[] outputData) {
        inputCursor = 0;
        outputCursor = 0;
        blockLength = 0;
        finalEol = new byte[0];
        eolsToFind = new byte[2][];
        inputData = data;
        inputLength = dataLength;
        this.outputData = outputData;
    }

    private void initFirstConversion() {
        info("New conversion started with data: ", inputData, inputLength);
    }

    private void detectDataEncoding() {
        encoding = detectEncoding(inputData);
    }

    private void initEols() {
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
            info("Eol to find: ", eolsToFind[i]);
        }
        info("Final Eol: ", finalEol);
    }

    private void initBlock() {
        blockLength = encoding.getLf().length;
        info("With an offset of: " + blockLength);
        blockMaximumLength = encoding.getCrLf().length;
        info("Reading blocks of length: " + blockMaximumLength);
        block = new byte[blockMaximumLength];
    }

    private void processBom() {
        if (hasBom(inputData)) {
            bom = encoding.getBom();
            for (int j = 0; j < bom.length; j++) {
                outputData[outputCursor++] = bom[j];
            }
            inputCursor += bom.length;
            info("Bom detected and written: ", bom);
        }
    }

    private boolean isWellInitiated() {
        return !isWrongInitiated();
    }

    private boolean hasMoreBlocks() {
        return inputCursor < inputLength;
    }

    private boolean isWrongInitiated() {
        return finalEol.length == 0 || blockLength <= 0
                || encoding == null || encoding instanceof UnknownEncoding;
    }

    private void initBlockRead() {
        line();
        info("Input cursor at: " + inputCursor);
    }

    private void readNextBlock() {
        for (int i = 0; i < blockMaximumLength; i++) {
            int blockCursor = inputCursor + i;
            if (blockCursor < inputData.length) {
                block[i] = inputData[inputCursor + i];
            }
        }
        info("Block read: ", block);
    }

    private void convertBlock() {
        int matchLength = startsWithAny(block, eolsToFind);
        if (isPositiveMatch(matchLength)) {
            info("Eol found at the beginnig of the block with length: " + matchLength);
            writeInOutput(finalEol, finalEol.length);
            inputCursor += matchLength;
            return;
        }
        writeInOutput(block, blockLength);
        inputCursor += blockLength;
    }

    private void writeInOutput(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            outputData[outputCursor++] = bytes[i];
        }
    }

    private void finishConversion() {
        outputLength = outputCursor;
        info("Data conversion finished with result: ", outputData, outputLength);
        isFirstConversion = false;
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public int getOutputLength() {
        return outputLength;
    }

}
