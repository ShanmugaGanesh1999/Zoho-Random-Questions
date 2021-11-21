package Set3;

public class reverseWordInSentence {
    public static void main(String[] args) {
        String string = "one two three";
        int i = string.length() - 1, k = string.length();
        for (; i >= 0; i--) {
            if (string.charAt(i) == ' ') {
                for (int j = i + 1; j < k; j++)
                    System.out.print(string.charAt(j));
                System.out.print(" ");
                k = i;
            }
        }
        for (int j = 0; j < k; j++)
            System.out.print(string.charAt(j));
    }
}
