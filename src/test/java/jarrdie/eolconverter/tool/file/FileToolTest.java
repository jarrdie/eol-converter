package jarrdie.eolconverter.tool.file;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.file.FileTool.getFileName;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import org.junit.*;
import static org.junit.Assert.*;

public class FileToolTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(FileToolTest.class);
    }

    @Test
    public void testExists() {
        assertTrue(exists(TMP_DIR));
        assertFalse(exists(TMP_DIR + "non_existing_dir"));
    }

    @Test
    public void testGetFileName() {
        assertEquals("", getFileName(null));
        assertEquals("", getFileName(""));
        assertEquals("afile", getFileName("afile"));
        assertEquals("afile.ext", getFileName("afile.ext"));
        assertEquals("afile.ext", getFileName("/afile.ext"));
        assertEquals("afile.ext", getFileName("\\afile.ext"));
        assertEquals("afile.ext", getFileName("/a/b/c/afile.ext"));
        assertEquals("afile.ext", getFileName("\\a\\b\\c\\afile.ext"));
        assertEquals("afile.ext", getFileName("\\a/b\\c/afile.ext"));
        assertEquals("afile.ext", getFileName("a/b/c/afile.ext"));
        assertEquals("c", getFileName("/a/b/c"));
        assertEquals("", getFileName("/a/b/c/"));
    }

}
