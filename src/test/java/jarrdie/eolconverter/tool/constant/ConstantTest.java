package jarrdie.eolconverter.tool.constant;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ConstantTest {

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(ConstantTest.class);
    }

    @Test
    public void testConstants() {
        assertTrue(EOL.equals(LF) || EOL.equals(CR) || EOL.equals(CRLF));
        assertTrue(FS.equals("/") || FS.equals("\\"));
        assertTrue(TMP_DIR.contains("tmp") || TMP_DIR.contains("Temp"));
    }

}
