package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.encoding.EncodingDetector.*;
import static jarrdie.eolconverter.tool.file.FileByteReader.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EncodingDetectorTest {

    InputStream input;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private void checkEncoding(String inputFile, Class expectedEncoding) throws Exception {
        input = openInput(inputFile);
        byte[] buffer = new byte[20];
        read(input, buffer);
        Encoding detectedEncoding = detectEncoding(buffer);
        assertTrue(expectedEncoding.isInstance(detectedEncoding));
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(EncodingDetectorTest.class);
    }

    @Test
    public void testDetectEncoding() throws Exception {
        checkEncoding("/123/lf_utf8_bom.bin", Utf8.class);
        checkEncoding("/123/lf_utf8_no_bom.bin", Utf8.class);
        checkEncoding("/123/lf_utf16le_bom.bin", Utf16le.class);
        checkEncoding("/123/lf_utf32le_bom.bin", Utf32le.class);
    }

    @Test
    public void testEncodingFound() {
        assertTrue(encodingFound(new Utf8()));
        assertFalse(encodingFound(new UnknownEncoding()));
    }

    @Test
    public void testDetectEncodingByBom() throws Exception {
        checkEncoding("/123/lf_utf8_bom.bin", Utf8.class);
        checkEncoding("/123/lf_utf16_bom.bin", Utf16le.class);
        checkEncoding("/123/lf_utf16le_bom.bin", Utf16le.class);
        checkEncoding("/123/lf_utf16be_bom.bin", Utf16be.class);
        checkEncoding("/123/lf_utf32le_bom.bin", Utf32le.class);
        checkEncoding("/123/lf_utf32be_bom.bin", Utf32be.class);

        checkEncoding("/123/cr_utf8_bom.bin", Utf8.class);
        checkEncoding("/123/cr_utf16_bom.bin", Utf16le.class);
        checkEncoding("/123/cr_utf16le_bom.bin", Utf16le.class);
        checkEncoding("/123/cr_utf16be_bom.bin", Utf16be.class);
        checkEncoding("/123/cr_utf32le_bom.bin", Utf32le.class);
        checkEncoding("/123/cr_utf32be_bom.bin", Utf32be.class);

        checkEncoding("/123/crlf_utf8_bom.bin", Utf8.class);
        checkEncoding("/123/crlf_utf16_bom.bin", Utf16le.class);
        checkEncoding("/123/crlf_utf16le_bom.bin", Utf16le.class);
        checkEncoding("/123/crlf_utf16be_bom.bin", Utf16be.class);
        checkEncoding("/123/crlf_utf32le_bom.bin", Utf32le.class);
        checkEncoding("/123/crlf_utf32be_bom.bin", Utf32be.class);
    }

    @Test
    public void testInferEncodingByContent() throws Exception {
        checkEncoding("/123/lf_utf8_no_bom.bin", Utf8.class);
        //TODO: enhance inference, currently: no bom -> utf8
        checkEncoding("/123/lf_utf16le_no_bom.bin", Utf8.class);
        checkEncoding("/123/lf_utf32le_no_bom.bin", Utf8.class);
    }

}
