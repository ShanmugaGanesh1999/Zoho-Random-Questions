package Set1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Q. Given a set of numbers like <10, 36, 54,89,12> we want to find sum of
 * weights based on the following conditions
 *  1. 5 if a perfect square 
 *  2. 4 if multiple of 4 and divisible by 6 
 *  3. 3 if even number
 * 
 * And sort the numbers based on the weight and print it as follows
 * <10,its_weight>,<36,its weight><89,its weight>
 * Should display the numbers based on increasing order.
 */

public class findSumOfWeights {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the length of the array ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("enter the array elements " + (i + 1) + " ");
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            int square = (int) Math.sqrt(arr[i]), count = 0;
            if (square * square == arr[i])
                count += 5;
            if (arr[i] % 4 == 0 || arr[i] % 6 == 0)
                count += 4;
            if (arr[i] % 2 == 0)
                count += 3;
            System.out.print("<" + arr[i] + "," + count + ">");
            if (i != n - 1)
                System.out.print(",");
        }
        scanner.close();
    }
}