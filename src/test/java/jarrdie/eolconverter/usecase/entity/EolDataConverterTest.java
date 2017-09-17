package jarrdie.eolconverter.usecase.entity;

import static java.io.File.separator;
import java.io.*;
import static java.lang.System.getProperty;
import org.junit.*;

public class EolDataConverterTest {

    private String inputFile;
    private FileInputStream inputStream;
    private String outputFile;
    private FileOutputStream outputStream;

    @Before
    public void setUp() {
        outputFile = getProperty("java.io.tmpdir") + separator + "out.bin";
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(outputFile);
    }

    @Test
    public void testConvertSingleFile() {
        inputFile = "crlf_utf8_bom.bin";
    }

}
