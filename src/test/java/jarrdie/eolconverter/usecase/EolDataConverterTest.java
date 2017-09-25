package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.file.FileByteReader.*;
import static jarrdie.eolconverter.tool.file.FileByteWriter.*;
import static jarrdie.eolconverter.tool.file.FileTool.*;
import static jarrdie.eolconverter.tool.log.SimpleLog.*;
import static jarrdie.eolconverter.usecase.EolConversion.*;
import java.io.*;
import java.nio.file.*;
import org.junit.*;

public class EolDataConverterTest {

    private String temporalDirectory;
    private String outputFile;
    private byte[] inputBuffer;
    private byte[] outputBuffer;
    private byte[] checkBuffer;

    @Before
    public void setUp() throws Exception {
        initTestDirectory();
        initBuffers(1024);
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

    @Test
    public void testConvertLangsFromCrLfToCr() throws Exception {
        initBuffers(16); //For debugging purposes only
        convert("/langs/crlf_utf16le.bin", CR);
        checkEqualsTo("/langs/cr_utf16le.bin");
    }

    @Test
    public void testConvertLangsFromCrLfToLf() throws Exception {
        convert("/langs/crlf_utf16le.bin", LF);
        checkEqualsTo("/langs/lf_utf16le.bin");
    }

    private void initTestDirectory() throws Exception {
        temporalDirectory = generateTemporalPath("eolconverter");
        regenerateDirectory(temporalDirectory);
    }

    private void initBuffers(int blockSize) throws Exception {
        inputBuffer = new byte[blockSize];
        outputBuffer = new byte[4 * blockSize];
        checkBuffer = new byte[blockSize];
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
        info("Checking results");
        int block = 0;
        try (InputStream inputEquivalentData = openInput(equivalentTestFile);
                InputStream inputOutData = openInput(equivalentTestFile)) {
            while (hasNext(inputEquivalentData)) {
                int lentgh = read(inputEquivalentData, inputBuffer);
                read(inputOutData, checkBuffer);
                String equivalentData = convertToHexadecimal(inputBuffer, lentgh);
                String outData = convertToHexadecimal(checkBuffer, lentgh);
                if (!equivalentData.equals(outData)) {
                    info("Diferences found at block: " + block);
                    info("Expected: " + equivalentData);
                    info("Found: " + outData);
                    assert false;
                }
                block++;
            }
            info("Blocks compared: " + block);
        }
    }

    @Test
    public void testGetOutputData() {
    }

    @Test
    public void testGetOutputLength() {
    }

}
