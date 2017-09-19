package jarrdie.eolconverter.tool.comparator;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ByteComparatorTest {

    @Test
    public void testAreEqual() throws Exception {
        assertTrue(areEqual("123".getBytes(), "123".getBytes()));
        assertFalse(areEqual("123".getBytes(), "a".getBytes()));
    }

    @Test
    public void testCompare() throws Exception {
        compare("123".getBytes(), "123".getBytes());
        assert true;
    }

    @Test(expected = Exception.class)
    public void testCompareFailure() throws Exception {
        compare("123".getBytes(), "a".getBytes());
        assert false;
    }

}
