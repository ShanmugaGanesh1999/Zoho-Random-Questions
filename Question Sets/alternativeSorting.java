package Set2;

// import java.util.Arrays;

public class alternativeSorting {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5, 6, 4, 7 };
        // Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
        for (int i = 0, j = arr.length - 1; i <= j; i++, j--) {
            if (i != j)
                System.out.print(arr[j] + " " + arr[i] + " ");
            else
                System.out.print(arr[i]);
        }
        // 7 1 6 2 5 3 4
    }
}
