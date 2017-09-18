package jarrdie.eolconverter.tool.comparator;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.areEqual;
import static jarrdie.eolconverter.tool.comparator.ByteComparator.compare;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

public class ByteComparatorTest {

    @Test
    public void testAreEqual() throws Exception {
        assertTrue(areEqual("123".getBytes(), "123".getBytes()));
        assertFalse(areEqual("123".getBytes(), "a".getBytes()));
    }

    @Test
    public void testCompare() throws Exception {
        compare("123".getBytes(), "123".getBytes());
    }

    @Test(expected = Exception.class)
    public void testCompareFailure() throws Exception {
        compare("123".getBytes(), "a".getBytes());
    }

}
