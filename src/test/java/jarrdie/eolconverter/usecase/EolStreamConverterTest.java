package jarrdie.eolconverter.usecase;

import static jarrdie.eolconverter.tool.directory.Directory.exists;
import static jarrdie.eolconverter.tool.directory.Directory.generateTemporalPath;
import static jarrdie.eolconverter.tool.directory.Directory.regenerateDirectory;
import static jarrdie.eolconverter.tool.directory.Directory.removeDirectory;
import static jarrdie.eolconverter.tool.file.FileByteReader.openInput;
import static jarrdie.eolconverter.tool.file.FileByteWriter.openOutput;
import static jarrdie.eolconverter.usecase.EolConversion.CRLF;
import java.io.*;
import static org.junit.Assert.assertTrue;
import org.junit.*;

public class EolStreamConverterTest {

    private String path;

    @Before
    public void setUp() throws Exception {
        path = generateTemporalPath("teststream");
        regenerateDirectory(path);
    }

    @After
    public void tearDown() throws Exception {
        removeDirectory(path);
    }

    @Test
    public void testConvert() throws Exception {
        final int blockSize = 1024;
        byte[] inputBuffer = new byte[blockSize];
        byte[] outputBuffer = new byte[4 * blockSize];
        EolDataConverter eolDataConverter = new EolDataConverter(CRLF);
        EolStreamConverter eolStreamConverter = new EolStreamConverter(eolDataConverter, inputBuffer, outputBuffer);
        try (InputStream input = openInput("/123/lf_utf8_bom.bin");
                OutputStream output = openOutput(path + "lf_utf8_bom_to_crlf.bin")) {
            eolStreamConverter.convert(input, output);
        }
        assertTrue(exists(path + "lf_utf8_bom_to_crlf.bin"));
    }

}
