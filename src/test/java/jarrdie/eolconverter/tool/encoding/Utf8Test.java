package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Utf8Test {

    private Encoding utf8;
    private byte[] bytes;

    @Before
    public void setUp() {
        utf8 = new Utf8();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(Utf8Test.class);
    }

    @Test
    public void testGetName() {
        assertEquals("UTF-8", utf8.getName());
    }

    @Test
    public void testGetBom() {
        bytes = utf8.getBom();
        assertNotNull(bytes);
        assertEquals(3, bytes.length);
        assertEquals("EF BB BF", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCr() {
        bytes = utf8.getCr();
        assertNotNull(bytes);
        assertEquals(1, bytes.length);
        assertEquals("0D", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetLf() {
        bytes = utf8.getLf();
        assertNotNull(bytes);
        assertEquals(1, bytes.length);
        assertEquals("0A", convertToHexadecimal(bytes));
    }

    @Test
    public void testGetCrLf() {
        bytes = utf8.getCrLf();
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
        assertEquals("0D 0A", convertToHexadecimal(bytes));
    }

}
