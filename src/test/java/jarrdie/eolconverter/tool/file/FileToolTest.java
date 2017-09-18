package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.constant.Constant.TMP_DIR;
import static jarrdie.eolconverter.tool.directory.Directory.exists;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

public class FileToolTest {

    @Test
    public void testExists() {
        assertTrue(exists(TMP_DIR));
        assertFalse(exists(TMP_DIR + "non_existing_dir"));
    }

}
