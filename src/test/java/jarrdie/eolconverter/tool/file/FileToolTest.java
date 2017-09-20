package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class FileToolTest {

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(FileToolTest.class);
    }

    @Test
    public void testExists() {
        assertTrue(exists(TMP_DIR));
        assertFalse(exists(TMP_DIR + "non_existing_dir"));
    }

}
