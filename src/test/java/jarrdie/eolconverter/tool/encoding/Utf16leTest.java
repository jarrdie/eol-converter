package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Utf16leTest {

    private Encoding utf16le;
    private byte[] bytes;

    @Before
    public void setUp() {
        utf16le = new Utf16le();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(Utf16leTest.class);
    }

    @Test
    public void testGetName() {
        assertEquals("UTF-16LE", utf16le.getName());
    }

    @Test
    public void testGetBom() {
        bytes = utf16le.getBom();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("FF FE", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCr() {
        bytes = utf16le.getCr();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("0D 00", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetLf() {
        bytes = utf16le.getLf();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("0A 00", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCrLf() {
        bytes = utf16le.getCrLf();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("0D 00 0A 00", convertToHexadecimal(bytes));
    }

}
