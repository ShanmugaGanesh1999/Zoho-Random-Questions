package Set2;

public class infixValidator {
    public static void main(String[] args) {
        String string = "((a+b)"; // (a+b)(a*b) -> Valid, (ab)(ab+) & ((a+b) -> Invalid
        int oscillator = 0, i = 0;
        for (; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(')
                oscillator++;
            else if (c == ')')
                oscillator--;
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                int prev = string.charAt(i - 1), next = string.charAt(i + 1);
                if (!((prev >= 97 && prev <= 122) && (next >= 97 && next <= 122)))
                    break;
            }
        }
        if (oscillator == 0 && i == string.length())
            System.out.println("Valid");
        else
            System.out.println("Invalid");
    }
}
