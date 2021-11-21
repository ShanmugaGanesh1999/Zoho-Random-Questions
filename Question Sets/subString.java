package Set3;

public class subString {
    public static void main(String[] args) {
        String string = "testing12", subString = "1234";
        // test123string & 123 -> 4, testing12 & 1234 -> -1
        int pos = -1;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == subString.charAt(0)) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            int i = 0, j = pos;
            for (; i < subString.length() && j < string.length();) {
                if (string.charAt(j++) != subString.charAt(i++)) {
                    pos = -1;
                    break;
                }
            }
            if (i != subString.length())
                pos = -1;
        }
        System.out.print(pos + " pos on string");
    }
}
