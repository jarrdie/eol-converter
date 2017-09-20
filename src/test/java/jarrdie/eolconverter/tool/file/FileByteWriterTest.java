package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.file.FileByteReader.*;
import static jarrdie.eolconverter.tool.file.FileByteWriter.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class FileByteWriterTest {

    private String path;
    private String outputFile;
    private OutputStream output;

    @Before
    public void setUp() throws Exception {
        path = generateTemporalPath("outdir");
        outputFile = path + EOF + "testout.bin";
        regenerateDirectory(path);
    }

    @After
    public void tearDown() throws Exception {
        close(output);
        removeDirectory(path);
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(FileByteWriterTest.class);
    }

    @Test(expected = Exception.class)
    public void testOpenOutputNull() throws Exception {
        openOutput(null);
    }

    @Test(expected = Exception.class)
    public void testOpenOutputEmpty() throws Exception {
        openOutput("");
    }

    @Test
    public void testOpenOutput() throws Exception {
        output = openOutput(outputFile);
        assertNotNull(output);
    }

    @Test
    public void testClose() throws Exception {
        output = openOutput(outputFile);
        close(output);
    }

    @Test
    public void testWrite() throws Exception {
        output = openOutput(outputFile);
        write(output, "1 2 3".getBytes());
        close(output);
        assertTrue(exists(outputFile));
        checkOutput();
    }

    @Test
    public void testWriteEmpty() throws Exception {
        write(null, null);
        assert true;
    }

    private void checkOutput() throws Exception {
        byte[] buffer = new byte[20];
        InputStream input = openInput(outputFile);
        int length = read(input, buffer);
        String data = convertToHexadecimal(buffer, length);
        assertEquals("31 20 32 20 33", data);
    }

}
