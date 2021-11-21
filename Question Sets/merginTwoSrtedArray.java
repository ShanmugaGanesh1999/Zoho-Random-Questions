package Set3;

public class merginTwoSrtedArray {
    public static void main(String[] args) {
        int[] arr1 = { 2, 4, 5, 6, 7, 9, 10, 13 }, arr2 = { 2, 3, 4, 5, 6, 7, 8, 9, 11, 15 };
        int i = 0, j = 0, k = 0;
        int[] arr3 = new int[arr1.length + arr2.length];
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                arr3[k++] = arr1[i++];
                j++;
            } else if (arr1[i] > arr2[j])
                arr3[k++] = arr2[j++];
            else if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
        }
        for (; i < arr1.length;)
            arr3[k++] = arr1[i++];
        for (; j < arr2.length;)
            arr3[k++] = arr2[j++];
        for (int l : arr3)
            if (l != 0)
                System.out.print(l + " ");
    }
}
