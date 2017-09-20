package jarrdie.eolconverter.tool.encoding;

import static jarrdie.eolconverter.tool.comparator.ByteComparator.*;
import java.util.*;

public class EncodingDetector {

    public static List<Encoding> encodings = new ArrayList<>();

    private static void initEncodingsInDetectionOrder() {
        if (encodings.size() > 0) {
            return;
        }
        addEncoding(new Utf32be());
        addEncoding(new Utf32le());
        addEncoding(new Utf16le());
        addEncoding(new Utf16be());
        addEncoding(new Utf8());
    }

    private static void addEncoding(Encoding encoding) {
        encodings.add(encoding);
    }

    public static Encoding detectEncoding(byte[] firstBytes) {
        initEncodingsInDetectionOrder();
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
        initEncodingsInDetectionOrder();
        for (Encoding encoding : encodings) {
            if (startsWith(firstBytes, encoding.getBom())) {
                return encoding;
            }
        }
        return new UnknownEncoding();
    }

    public static Encoding inferEncodingByContent(byte[] firstBytes) {
        initEncodingsInDetectionOrder();
        Encoding encoding = new Utf8();  //As stated in the RFC
        //TODO: alternatively, apply a smart set of heuristics
        return encoding;
    }

}
