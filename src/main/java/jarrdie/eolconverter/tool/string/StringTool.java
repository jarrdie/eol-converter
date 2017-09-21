package jarrdie.eolconverter.tool.string;

import static java.util.regex.Pattern.quote;

public class StringTool {

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String[] splitLiteral(String text, String divisor) {
        if (isEmpty(text) || isEmpty(divisor)) {
            return new String[0];
        }
        return text.split(quote(divisor));
    }

}
