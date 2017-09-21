package jarrdie.eolconverter.usecase;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class EolConversionTest {

    @Test
    public void testValues() {
        assertEquals(3, EolConversion.values().length);
    }

    @Test
    public void testValueOf() {
        assertEquals(EolConversion.CR, EolConversion.valueOf("CR"));
    }

}
