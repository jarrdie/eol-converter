package jarrdie.eolconverter.tool.string;

import static jarrdie.eolconverter.tool.string.StringTool.*;
import jarrdie.eolconverter.tool.test.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StringToolTest extends TestTool {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

    @Test
    public void testSplitLiteral() {
        assertNotNull(splitLiteral(null, null));
        assertEquals(0, splitLiteral(null, null).length);
        assertEquals(2, splitLiteral("a/b", "/").length);
        assertEquals(2, splitLiteral("a\\b", "\\").length);
        assertEquals(1, splitLiteral("a text", "unexisting").length);
        assertEquals("a text", splitLiteral("a text", "unexisting")[0]);
    }

}
