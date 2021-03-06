package jarrdie.eolconverter.tool.converter;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.converter.NumericConverter.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
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
    public void testConstructor() throws Exception {
        testDefaultConstructor(NumericConverterTest.class);
    }

    @Test
    public void testConvertToHexadecimal_byteArr() {
        String data = convertToHexadecimal(bytes);
        assertEquals("31 32 33", data);
    }

    @Test
    public void testConvertToHexadecimal_byteArr_int() {
        String data = convertToHexadecimal(bytes, 2);
        assertEquals("31 32", data);
    }

    @Test
    public void testConvertToHexadecimalEmpty() {
        assertEquals("", convertToHexadecimal(null));
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
    public void testConvertToHexadecimalListEmpty() {
        List data = convertToHexadecimalList(null);
        assertNotNull(data);
        assertEquals(0, data.size());
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

    @Test
    public void testConvertToHexadecimalMatrixEmpty() {
        assertEquals("", convertToHexadecimalMatrix(null));
    }

    @Test
    public void testConvertFromHexadecimal() {
        String hexadecimal = "00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F";
        byte[] bytes = convertFromHexadecimal(hexadecimal);
        String data = convertToHexadecimal(bytes);
        assertEquals(hexadecimal, data);
    }

    @Test
    public void testConvertFromHexadecimalEmpty() {
        byte[] bytes = convertFromHexadecimal(null);
        assertNotNull(bytes);
        assertEquals(0, bytes.length);
    }

}
