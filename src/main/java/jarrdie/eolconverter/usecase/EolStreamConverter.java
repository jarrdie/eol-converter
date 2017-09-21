package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.file.FileByteReader.hasNext;
import static jarrdie.eolconverter.tool.file.FileByteReader.read;
import static jarrdie.eolconverter.tool.file.FileByteWriter.write;
import java.io.*;

public class EolStreamConverter {

    private EolDataConverter eolConverter;
    private byte[] inputBuffer;
    private byte[] outputBuffer;
    private int totalInputLength;
    private int totalOutputLength;

    public EolStreamConverter(EolDataConverter eolConverter, byte[] inputBuffer, byte[] outputBuffer) {
        this.eolConverter = eolConverter;
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        this.totalInputLength = 0;
        this.totalOutputLength = 0;
    }

    public void convert(InputStream input, OutputStream output) throws Exception {
        while (hasNext(input)) {
            int length = read(input, inputBuffer);
            totalInputLength += length;
            eolConverter.convert(inputBuffer, length, outputBuffer);
            outputBuffer = eolConverter.getOutputData();
            int outputLength = eolConverter.getOutputLength();
            write(output, outputBuffer, outputLength);
            totalOutputLength += outputLength;
        }
    }

    public int getTotalInputLength() {
        return totalInputLength;
    }

    public int getTotalOutputLength() {
        return totalOutputLength;
    }

}
