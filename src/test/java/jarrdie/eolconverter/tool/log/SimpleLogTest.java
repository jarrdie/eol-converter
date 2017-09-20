package jarrdie.eolconverter.tool.log;

import static jarrdie.eolconverter.tool.log.SimpleLog.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import org.junit.*;

public class SimpleLogTest {

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(SimpleLogTest.class);
    }

    @Test
    public void testInfo_String() {
        info("hi");
    }

    @Test
    public void testInfo_byteArr() {
        byte[] bytes = "123".getBytes();
        info(bytes);

        info("1234567891011121314151617181920".getBytes());
    }

}
