package jarrdie.eolconverter.tool.converter;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class NumericConverterTest {

    private byte[] bytes;

    @Before
    public void setUp() {
        bytes = "123".getBytes();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConvertToHexadecimal() {
        String data = convertToHexadecimal(bytes);
        assertEquals("31 32 33", data);
    }

    @Test
    public void testConvertToHexadecimalList() {
        List data = convertToHexadecimalList(bytes);
        assertEquals(3, data.size());
        assertEquals("31", data.get(0));
        assertEquals("32", data.get(1));
        assertEquals("33", data.get(2));
    }

    @Test
    public void testConvertToHexadecimalMatrix() {
        String matrix = convertToHexadecimalMatrix(bytes);
        String[] parts = matrix.split(EOL);
        assertEquals(1, parts.length);
        String data = parts[0];
        assertEquals("31 32 33", data);

        bytes = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19".getBytes();
        matrix = convertToHexadecimalMatrix(bytes);
        parts = matrix.split(EOL);
        assertEquals(5, parts.length);
        assertEquals("31 20 32 20 33 20 34 20 35 20", parts[0]);
        assertEquals("37 20 31 38 20 31 39", parts[4]);
    }

}
