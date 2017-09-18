package jarrdie.eolconverter.tool.converter;

import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class NumericConverterTest {

    public NumericConverterTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConvertToHexadecimal() {
        byte[] bytes = "123".getBytes();
        String data = NumericConverter.convertToHexadecimal(bytes);
        assertEquals("31 32 33", data);
    }

    @Test
    public void testConvertToHexadecimalList() {
    }

    @Test
    public void testConvertToHexadecimalMatrix() {
    }

}
