package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.converter.NumericConverter.convertToHexadecimal;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.file.FileByteReader.closeInput;
import static jarrdie.eolconverter.tool.file.FileByteReader.hasNext;
import static jarrdie.eolconverter.tool.file.FileByteReader.openInput;
import static jarrdie.eolconverter.tool.file.FileByteReader.read;
import static jarrdie.eolconverter.tool.file.FileByteWriter.openOutput;
import static jarrdie.eolconverter.tool.file.FileTool.getFileName;
import static jarrdie.eolconverter.usecase.EolConversion.*;
import java.io.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class EolDataConverterTest {

    private String temporalDirectory;
    private String outputFile;
    private byte[] inputBuffer;
    private byte[] outputBuffer;
    private int inputTotalLength;
    private int outputTotalLength;

    @Before
    public void setUp() throws Exception {
        initTestDirectory();
        initBuffers();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConvert() throws Exception {
        convert("/123/lf_utf8_bom.bin", CRLF);
        checkInputLength(9);
        checkOutputLength(12);
        checkOutputFile("/123/crlf_utf8_bom.bin");
    }

    private void initTestDirectory() throws Exception {
        temporalDirectory = generateTemporalPath("eolconverter");
        regenerateDirectory(temporalDirectory);
    }

    private void initBuffers() throws Exception {
        final int blockSize = 1024;
        inputBuffer = new byte[blockSize];
        outputBuffer = new byte[4 * blockSize];
    }

    private void convert(String inputFile, EolConversion eolConversion) throws Exception {
        EolDataConverter eolDataConverter = new EolDataConverter(eolConversion);
        EolStreamConverter eolStreamConverter = new EolStreamConverter(eolDataConverter, inputBuffer, outputBuffer);
        outputFile = generateOutputFileName(eolConversion, inputFile);
        try (InputStream input = openInput(inputFile);
                OutputStream output = openOutput(outputFile)) {
            eolStreamConverter.convert(input, output);
            inputTotalLength = eolStreamConverter.getTotalInputLength();
            outputTotalLength = eolStreamConverter.getTotalOutputLength();
        }
    }

    private String generateOutputFileName(EolConversion eolConversion, String inputFile) {
        String eol = eolConversion.name().toLowerCase();
        String inputFileName = getFileName(inputFile);
        return temporalDirectory + inputFileName.replace(".bin", "_to_" + eol + ".bin");
    }

    private void checkInputLength(int expectedInputTotalLength) {
        assertEquals(expectedInputTotalLength, inputTotalLength);
    }

    private void checkOutputLength(int expectedOutputTotalLength) {
        assertEquals(expectedOutputTotalLength, outputTotalLength);
    }

    private void checkOutputFile(String equivalentTestFile) throws Exception {
        String testData = readFile(equivalentTestFile);
        String outData = readFile(outputFile);
        assertEquals(testData, outData);
    }

    private String readFile(String file) throws Exception {
        StringBuffer data = new StringBuffer();
        InputStream input = openInput(file);
        while (hasNext(input)) {
            int length = read(input, inputBuffer);
            String readData = convertToHexadecimal(inputBuffer, length);
            data.append(" " + readData);
        }
        closeInput(input);
        return data.toString();
    }

}
