package Set3;

public class charCountPrinting {
    public static void main(String[] args) {
        String str = "b3c6d15"; // a1b10 & b3c6d15
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int a = (int) (str.charAt(++i) - 48);
            if ((int) (str.charAt(i + 1) - 48) >= 0 && (int) (str.charAt(i + 1) - 48) <= 9) {
                a = a * 10 + (int) (str.charAt(++i) - 48);
            }
            for (int j = 0; j < a; j++)
                System.out.print(c);
        }
    }
}
