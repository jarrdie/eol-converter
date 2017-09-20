package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Utf16beTest {

    private Encoding utf16be;
    private byte[] bytes;

    @Before
    public void setUp() {
        utf16be = new Utf16be();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(Utf16beTest.class);
    }

    @Test
    public void testGetName() {
        assertEquals("UTF-16BE", utf16be.getName());
    }

    @Test
    public void testGetBom() {
        bytes = utf16be.getBom();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("FE FF", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCr() {
        bytes = utf16be.getCr();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("00 0D", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetLf() {
        bytes = utf16be.getLf();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("00 0A", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCrLf() {
        bytes = utf16be.getCrLf();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("00 0D 00 0A", convertToHexadecimal(bytes));
    }

}
