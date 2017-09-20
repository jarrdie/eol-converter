package jarrdie.eolconverter.tool.directory;

import static jarrdie.eolconverter.tool.constant.Constant.*;
import static jarrdie.eolconverter.tool.directory.Directory.*;
import static jarrdie.eolconverter.tool.test.TestTool.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DirectoryTest {

    private String path;

    @Before
    public void setUp() {
        path = generateTemporalPath("testdir");
    }

    @After
    public void tearDown() throws Exception {
        removeDirectory(path);
    }

    @Test
    public void testConstructor() throws Exception {
        testDefaultConstructor(DirectoryTest.class);
    }

    @Test
    public void testExists() {
        assertTrue(exists(TMP_DIR));
        assertFalse(exists(TMP_DIR + "non_existing_dir"));
    }

    @Test
    public void testGenerateTemporalPath() {
        assertTrue(path.contains("tmp") || path.contains("temp"));
        assertTrue(path.contains("testdir"));
        assertTrue(path.contains(EOF));
    }

    @Test
    public void testRegenerateDirectory() throws Exception {
        regenerateDirectory(path);
        assertTrue(exists(path));
    }

    @Test
    public void testCreateDirectory() throws Exception {
        removeDirectory(path);
        assertFalse(exists(path));
        createDirectory(path);
        assertTrue(exists(path));
        removeDirectory(path);
        assertFalse(exists(path));
    }

    @Test
    public void testRemoveDirectory() throws Exception {
        testCreateDirectory();
    }

}
