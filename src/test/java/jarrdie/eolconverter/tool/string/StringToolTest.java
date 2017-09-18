package jarrdie.eolconverter.tool.string;

import static jarrdie.eolconverter.tool.string.StringTool.isEmpty;
import org.junit.*;
import static org.junit.Assert.*;

public class StringToolTest {

    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(""));
        assertFalse(isEmpty("a"));
    }

}
