package Set3;

public class positionAsecDescSort {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 5, 6, 7, 8, 4, 9 };
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i % 2 == 0)
                System.out.print(arr[i] + " ");
            else
                System.out.print(arr[arr.length - i - 1] + " ");

        }
    }
}
