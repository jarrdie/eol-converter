package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.file.FileByteReader.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;

public class FileByteReaderTest {

    InputStream input;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        close(input);
    }

    @Test(expected = Exception.class)
    public void testOpenInputNull() throws Exception {
        openInput(null);
    }

    @Test(expected = Exception.class)
    public void testOpenInputEmpty() throws Exception {
        openInput("");
    }

    @Test
    public void testOpenInput() throws Exception {
        input = openInput("/123/lf_utf8_bom.bin");
        assertNotNull(input);
    }

    @Test
    public void testClose() throws Exception {
        input = openInput("/123/lf_utf8_bom.bin");
        close(input);
    }

    @Test
    public void testRead() throws Exception {
        input = openInput("/123/lf_utf8_bom.bin");
        byte[] buffer = new byte[20];
        int length = read(input, buffer);
        assertEquals(9, length);
        String data = convertToHexadecimal(buffer, length);
        assertTrue(data.contains("31 0A 32 0A 33 0A"));
    }

}
