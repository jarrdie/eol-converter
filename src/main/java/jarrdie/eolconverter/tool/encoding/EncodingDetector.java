package jarrdie.eolconverter.tool.encoding;

import java.util.*;

public class EncodingDetector {

    public static List<Encoding> encodings = new ArrayList<>();

    private static void initEncodings() {
        if (encodings.size() > 0) {
            return;
        }
        addEncoding(new Utf8());
        addEncoding(new Utf16be());
        addEncoding(new Utf16le());
        addEncoding(new Utf32be());
        addEncoding(new Utf32le());
    }

    private static void addEncoding(Encoding encoding) {
        if (encoding == null) {
            return;
        }
        encodings.add(encoding);
    }

    public static Encoding detectEncoding(byte[] firstBytes) {
        initEncodings();
        Encoding encoding = detectEncodingByBom(firstBytes);
        if (encodingFound(encoding)) {
            return encoding;
        }
        return inferEncodingByContent(firstBytes);
    }

    public static boolean encodingFound(Encoding encoding) {
        return !(encoding instanceof UnknownEncoding);
    }

    public static Encoding detectEncodingByBom(byte[] firstBytes) {
        initEncodings();
        for (Encoding encoding : encodings) {
            if (Arrays.equals(firstBytes, encoding.getBom())) {
                return encoding;
            }
        }
        return new UnknownEncoding();
    }

    public static Encoding inferEncodingByContent(byte[] firstBytes) {
        initEncodings();
        Encoding encoding = new Utf8();  //As stated in the RFC
        //TODO: alternatively, apply a smart set of heuristics
        return encoding;
    }

}
