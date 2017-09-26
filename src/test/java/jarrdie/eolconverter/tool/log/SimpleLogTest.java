package jarrdie.eolconverter.tool.log;

import static jarrdie.eolconverter.tool.log.SimpleLog.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import org.junit.*;

public class SimpleLogTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

    @Test
    public void testInfo_String_byteArr() {
        info("Hex result: ", "123".getBytes());
    }

    @Test
    public void testInfo_3args() {
        info("Hex result: ", "123".getBytes(), 2);
    }

    @Test
    public void testLine() {
        line();
    }

}
