package Set1;

import java.util.Scanner;

/*
program
p     m
 r   a
  o r
   g
  o r
 r   a
p     m
*/
public class printStringAsX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the text ");
        String text = scanner.nextLine();
        int textLength = text.length();
        // if (textLength % 2 == 0) {
        // System.out.print("please enter a text with odd length");
        // return;
        // }
        for (int i = 0; i < textLength; i++) {
            for (int j = 0; j < textLength; j++) {
                if (i == j) {
                    System.out.print(text.charAt(i));
                } else if (i + j + 1 == textLength) {
                    System.out.print(text.charAt(j));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}
