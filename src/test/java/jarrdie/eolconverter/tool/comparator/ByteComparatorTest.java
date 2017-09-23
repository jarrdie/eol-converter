package jarrdie.eolconverter.tool.comparator;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ByteComparatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(ByteComparatorTest.class);
    }

    @Test
    public void testAreEqual() {
        assertTrue(areEqual("123".getBytes(), "123".getBytes()));
        assertFalse(areEqual("123".getBytes(), "a".getBytes()));
    }

    @Test
    public void testAreEqualEmpty() {
        assertFalse(areEqual(null, null));
    }

    @Test
    public void testCompare() throws Exception {
        compare("123".getBytes(), "123".getBytes());
        assert true;
    }

    @Test(expected = Exception.class)
    public void testCompareEmpty() throws Exception {
        compare(null, null);
        assert false;
    }

    @Test(expected = Exception.class)
    public void testCompareFailure() throws Exception {
        compare("123".getBytes(), "a".getBytes());
        assert false;
    }

    @Test
    public void testStartsWith() {
        assertTrue(startsWith("123456789".getBytes(), "123".getBytes()));
        assertFalse(startsWith("123456789".getBytes(), "999".getBytes()));
        assertFalse(startsWith("12".getBytes(), "123".getBytes()));
    }

    @Test
    public void testStartsWithEmpty() {
        assertFalse(startsWith(null, null));
    }

    @Test
    public void testStartsWithAny() {
        byte[][] matrix = new byte[2][];
        matrix[0] = "123".getBytes();
        matrix[1] = "456".getBytes();
        assertTrue(isPositiveMatch(startsWithAny("123456789".getBytes(), matrix)));
        assertFalse(isPositiveMatch(startsWithAny("789".getBytes(), matrix)));
    }

    @Test
    public void testStartsWithAnyEmpty() {
        assertFalse(isPositiveMatch(startsWithAny(null, null)));
    }
}
