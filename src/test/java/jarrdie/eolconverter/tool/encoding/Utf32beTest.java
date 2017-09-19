package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Utf32beTest {

    private Encoding utf32be;
    private byte[] bytes;

    @Before
    public void setUp() {
        utf32be = new Utf32be();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        assertEquals("UTF-32BE", utf32be.getName());
    }

    @Test
    public void testGetBom() {
        bytes = utf32be.getBom();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("00 00 FE FF", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCr() {
        bytes = utf32be.getCr();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("00 00 00 0D", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetLf() {
        bytes = utf32be.getLf();
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
        assertEquals("00 00 00 0A", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCrLf() {
        bytes = utf32be.getCrLf();
        assertNotNull(bytes);
        assertEquals(8, bytes.length);
        assertEquals("00 00 00 0D 00 00 00 0A", convertToHexadecimal(bytes));
    }

}
