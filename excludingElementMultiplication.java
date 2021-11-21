/**
 * excludingElementMultiplication
 */
public class excludingElementMultiplication {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        // normalMethed(arr);
        efficientMethod(arr);
    }

    private static void efficientMethod(int[] arr) { // O(N)
        int n = arr[0];
        for (int i = 1; i < arr.length; i++)
            n *= arr[i];
        for (int i = 0; i < arr.length; i++)
            System.out.print((int) (n / arr[i]) + " ");

    }

    // private static void normalMethed(int[] arr) { // O(N^2)
    // for (int i = 0; i < arr.length; i++) {
    // int m = 1;
    // for (int j = 0; j < arr.length; j++) {
    // if (i != j)
    // m *= arr[j];
    // }
    // System.out.print(m + " "); // 24 12 8 6
    // }
    // }
}