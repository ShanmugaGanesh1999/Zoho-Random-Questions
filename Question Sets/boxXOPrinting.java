package Set4;

public class boxXOPrinting {
    public static void main(String[] args) {
        int n = 7, m = 6;
        char[][] matrix = new char[n][m];
        int top = 0;
        int down = n - 1;
        int left = 0;
        int right = m - 1;
        char c;
        while (true) {
            if (top % 2 == 0)
                c = 'X';
            else
                c = 'O';
            // print first n
            for (int i = left; i <= right; ++i)
                matrix[top][i] = c;
            top++;
            if (top > down || left > right)
                break;
            // print last mumn
            for (int i = top; i <= down; ++i)
                matrix[i][right] = c;
            right--;
            if (top > down || left > right)
                break;
            // print last n
            for (int i = right; i >= left; --i)
                matrix[down][i] = c;
            down--;
            if (top > down || left > right)
                break;
            // print first mumn
            for (int i = down; i >= top; --i)
                matrix[i][left] = c;
            left++;
            if (top > down || left > right)
                break;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
