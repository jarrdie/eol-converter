package jarrdie.eolconverter.tool.string;

import static jarrdie.eolconverter.tool.string.StringTool.*;
import jarrdie.eolconverter.tool.test.*;
import static org.junit.Assert.*;
import org.junit.*;

public class StringToolTest extends TestTool {

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(StringToolTest.class);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(""));
        assertFalse(isEmpty("a"));
    }

}
