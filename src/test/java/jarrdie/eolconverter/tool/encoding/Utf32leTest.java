package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Utf32leTest {

    private Encoding utf32le;
    private byte[] bytes;

    @Before
    public void setUp() {
        utf32le = new Utf32le();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        assertEquals("UTF-32LE", utf32le.getName());
    }

    @Test
    public void testGetBom() {
        bytes = utf32le.getBom();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("FF FE 00 00", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCr() {
        bytes = utf32le.getCr();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("0D 00 00 00", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetLf() {
        bytes = utf32le.getLf();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("0A 00 00 00", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCrLf() {
        bytes = utf32le.getCrLf();
        assertNotNull(bytes);
        assertEquals(8, bytes.length);
        assertEquals("0D 00 00 00 0A 00 00 00", convertToHexadecimal(bytes));
    }

}
