package Set1;

import java.util.Scanner;

/**
 * Q. Save the string “WELCOMETOZOHOCORPORATION” in a two dimensional array and
 * search for substring like “too” in the two dimensional string both from left
 * to right and from top to bottom.
 * 
 * w e L C O M E T O Z O H O C O R P O R A T I O n
 * 
 * And print the start and ending index as
 * 
 * Start index : <1,2>
 * 
 * End index: <3, 2>
 */

public class saveStringIn2D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the text ");
        String text = scanner.nextLine();
        int n = text.length(), col = 5, row = n / col + 1, k = 0;
        char[][] arr = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (k < n)
                    arr[i][j] = text.charAt(k++);
                else
                    arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("enter the text to search ");
        String search = scanner.nextLine();
        int m = search.length();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Boolean flag = true;
                int l = 0;
                // row search
                if (flag && search.charAt(0) == arr[i][j]) {
                    for (l = 0; l < m; l++) {
                        if (search.charAt(l) != arr[i][j + l]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.print("<" + i + "," + j + ">" + "\n<" + i + "," + (j + l - 1) + ">");
                    }
                }

            }
        }
        scanner.close();
    }
}
