package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UnknownEncodingTest {

    private Encoding unknown;

    @Before
    public void setUp() {
        unknown = new UnknownEncoding();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(UnknownEncodingTest.class);
    }

    @Test
    public void testGetName() {
        assertEquals("Unknown", unknown.getName());

    }

    @Test
    public void testGetBom() {
        assertEquals(0, unknown.getBom().length);
    }

    @Test
    public void testGetCr() {
        assertEquals(0, unknown.getCr().length);
    }

    @Test
    public void testGetLf() {
        assertEquals(0, unknown.getLf().length);
    }

    @Test
    public void testGetCrLf() {
        assertEquals(0, unknown.getCrLf().length);
    }

}
