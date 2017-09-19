package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.usecase.EolConversion.*;
import java.io.*;
import org.junit.*;

public class EolDataConverterTest {

    private String temporalDirectory;
    private String testsDirectory;
    private String inputFile;
    private String outputFile;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;

    private byte[] readBuffer;
    private byte[] writeBuffer;

    private EolDataConverter eolConverter;
    private EolConversion eolConversion;

    private void initDeterministicTests() {

    }

    private void initProbabilisticTests() {

    }

    private void initTestDirectory() throws Exception {
        temporalDirectory = generateTemporalPath("eolconverter");
        regenerateDirectory(temporalDirectory);
        testsDirectory = "123";
    }

    private void initBuffers() throws Exception {
        final int blockSize = 1024;
        readBuffer = new byte[blockSize];
        writeBuffer = new byte[4 * blockSize];
    }

    private void convert() throws Exception {
        eolConverter = new EolDataConverter(eolConversion);
        String eol = eolConversion.name().toLowerCase();
        outputFile = inputFile.replace(".bin", "_to_" + eol + ".bin");
    }

    @Before
    public void setUp() throws Exception {
        initTestDirectory();
        initBuffers();
    }

    @After
    public void tearDown() throws Exception {
        convert();
        System.out.println(outputFile);
    }

    @Test
    public void testConvert() {
        eolConversion = CRLF;
        inputFile = "lf_utf8_bom.bin";
        outputFile = "lf_utf8_bom_to_cr.bin";
    }

}
