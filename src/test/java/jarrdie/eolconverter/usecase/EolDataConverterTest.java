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
import java.nio.file.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class EolDataConverterTest {

    private String temporalDirectory;
    private String outputFile;
    private byte[] inputBuffer;
    private byte[] outputBuffer;

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
        checkEqualsTo("/123/crlf_utf8_bom.bin");
    }

    @Test
    public void testConvertFromLfToCr() throws Exception {
        convert("/123/lf_utf8_bom.bin", CR);
        checkEqualsTo("/123/cr_utf8_bom.bin");

        convert("/123/lf_utf16le_bom.bin", CR);
        checkEqualsTo("/123/cr_utf16le_bom.bin");

        convert("/123/lf_utf16be_bom.bin", CR);
        checkEqualsTo("/123/cr_utf16be_bom.bin");

        convert("/123/lf_utf32le_bom.bin", CR);
        checkEqualsTo("/123/cr_utf32le_bom.bin");

        convert("/123/lf_utf32be_bom.bin", CR);
        checkEqualsTo("/123/cr_utf32be_bom.bin");
    }

    @Test
    public void testConvertFromLfToCrLf() throws Exception {
        convert("/123/lf_utf8_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf8_bom.bin");

        convert("/123/lf_utf16le_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf16le_bom.bin");

        convert("/123/lf_utf16be_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf16be_bom.bin");

        convert("/123/lf_utf32le_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf32le_bom.bin");

        convert("/123/lf_utf32be_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf32be_bom.bin");
    }

    @Test
    public void testConvertFromCrToLf() throws Exception {
        convert("/123/cr_utf8_bom.bin", LF);
        checkEqualsTo("/123/lf_utf8_bom.bin");

        convert("/123/cr_utf16le_bom.bin", LF);
        checkEqualsTo("/123/lf_utf16le_bom.bin");

        convert("/123/cr_utf16be_bom.bin", LF);
        checkEqualsTo("/123/lf_utf16be_bom.bin");

        convert("/123/cr_utf32le_bom.bin", LF);
        checkEqualsTo("/123/lf_utf32le_bom.bin");

        convert("/123/cr_utf32be_bom.bin", LF);
        checkEqualsTo("/123/lf_utf32be_bom.bin");
    }

    @Test
    public void testConvertFromCrToCrLf() throws Exception {
        convert("/123/cr_utf8_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf8_bom.bin");

        convert("/123/cr_utf16le_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf16le_bom.bin");

        convert("/123/cr_utf16be_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf16be_bom.bin");

        convert("/123/cr_utf32le_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf32le_bom.bin");

        convert("/123/cr_utf32be_bom.bin", CRLF);
        checkEqualsTo("/123/crlf_utf32be_bom.bin");
    }

    @Test
    public void testConvertFromCrLfToLf() throws Exception {
        convert("/123/crlf_utf8_bom.bin", LF);
        checkEqualsTo("/123/lf_utf8_bom.bin");

        convert("/123/crlf_utf16le_bom.bin", LF);
        checkEqualsTo("/123/lf_utf16le_bom.bin");

        convert("/123/crlf_utf16be_bom.bin", LF);
        checkEqualsTo("/123/lf_utf16be_bom.bin");

        convert("/123/crlf_utf32le_bom.bin", LF);
        checkEqualsTo("/123/lf_utf32le_bom.bin");

        convert("/123/crlf_utf32be_bom.bin", LF);
        checkEqualsTo("/123/lf_utf32be_bom.bin");
    }

    @Test
    public void testConvertFromCrLfToCr() throws Exception {
        convert("/123/crlf_utf8_bom.bin", CR);
        checkEqualsTo("/123/cr_utf8_bom.bin");

        convert("/123/crlf_utf16le_bom.bin", CR);
        checkEqualsTo("/123/cr_utf16le_bom.bin");

        convert("/123/crlf_utf16be_bom.bin", CR);
        checkEqualsTo("/123/cr_utf16be_bom.bin");

        convert("/123/crlf_utf32le_bom.bin", CR);
        checkEqualsTo("/123/cr_utf32le_bom.bin");

        convert("/123/crlf_utf32be_bom.bin", CR);
        checkEqualsTo("/123/cr_utf32be_bom.bin");
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
        copyInputFile(inputFile);
        outputFile = generateOutputFileName(eolConversion, inputFile);
        try (InputStream input = openInput(inputFile);
                OutputStream output = openOutput(outputFile)) {
            eolStreamConverter.convert(input, output);
        }
    }

    private void copyInputFile(String inputFile) throws Exception {
        String inputFileCopy = temporalDirectory + getFileName(inputFile);
        try (InputStream input = openInput(inputFile)) {
            Files.copy(input, Paths.get(inputFileCopy));
        }
    }

    private String generateOutputFileName(EolConversion eolConversion, String inputFile) {
        String eol = eolConversion.name().toLowerCase();
        String inputFileName = getFileName(inputFile);
        return temporalDirectory + inputFileName.replace(".bin", "_to_" + eol + ".bin");
    }

    private void checkEqualsTo(String equivalentTestFile) throws Exception {
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

    @Test
    public void testGetOutputData() {
    }

    @Test
    public void testGetOutputLength() {
    }

}
