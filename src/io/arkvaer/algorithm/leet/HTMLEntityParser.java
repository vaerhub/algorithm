package io.arkvaer.algorithm.leet;

public class HTMLEntityParser {
        public static String entityParser(String text) {
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            int len = text.length();
            while (idx < len) {
                char c = text.charAt(idx);
                if (c == '&' && len - idx >= 4) {
                    if (text.charAt(idx + 3) == ';') {
                        if (text.charAt(idx + 2) == 't' && text.charAt(idx + 1) == 'g') {
                            sb.append('>');
                            idx += 4;
                        } else if (text.charAt(idx + 2) == 't' && text.charAt(idx + 1) == 'l') {
                            sb.append('<');
                            idx += 4;
                        } else {
                            sb.append(c);
                            idx++;
                        }
                    } else if (len - idx >= 5 && text.charAt(idx + 4) == ';') {
                        if (text.charAt(idx + 2) == 'm' && text.charAt(idx + 1) == 'a' && text.charAt(idx + 3) == 'p') {
                            sb.append('&');
                            idx += 5;
                        } else {
                            sb.append(c);
                            idx++;
                        }
                    } else if (len - idx >= 6 && text.charAt(idx + 5) == ';') {
                        if (text.charAt(idx + 1) == 'a' && text.charAt(idx + 2) == 'p' && text.charAt(idx + 3) == 'o' && text.charAt(idx + 4) == 's') {
                            sb.append('\'');
                            idx += 6;
                        } else if (text.charAt(idx + 1) == 'q' && text.charAt(idx + 2) == 'u' && text.charAt(idx + 3) == 'o' && text.charAt(idx + 4) == 't') {
                            sb.append('\"');
                            idx += 6;
                        } else {
                            sb.append(c);
                            idx++;
                        }
                    } else if (len - idx >= 7 && text.charAt(idx + 6) == ';') {
                        if (text.charAt(idx + 1) == 'f' && text.charAt(idx + 2) == 'r' && text.charAt(idx + 3) == 'a' && text.charAt(idx + 4) == 's' && text.charAt(idx + 5) == 'l') {
                            sb.append('/');
                            idx += 7;
                        } else {
                            sb.append(c);
                            idx++;
                        }
                    } else {
                        sb.append(c);
                        idx++;
                    }
                } else {
                    sb.append(c);
                    idx++;
                }
            }
            return sb.toString();
    }

    public static void main(String[] args) {
        String[] texts = {"&&&", "&amp; is an HTML entity but &ambassador; is not.", "&&lt;"};
        for (String text : texts) {
            System.out.println(entityParser(text));
        }
    }
}
