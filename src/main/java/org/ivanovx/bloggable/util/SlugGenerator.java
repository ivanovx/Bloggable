package org.ivanovx.bloggable.util;

public class SlugGenerator {
    public static String toSlug(String uglyString) {
        StringBuffer result = new StringBuffer(uglyString.length());
        boolean isLastCharacterDash = false;

        for (char c : uglyString.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
                isLastCharacterDash = false;
            } else if (!isLastCharacterDash) {
                result.append('-');
                isLastCharacterDash = true;
            }
        }

        return result.toString().replace(' ', '-');
    }
}
