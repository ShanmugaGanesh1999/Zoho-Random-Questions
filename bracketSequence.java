import java.util.Scanner;
import java.util.Stack;

public class bracketSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the bracket sequence ");
        String seq = scanner.next();
        System.out.print("enter the start ");
        int start = scanner.nextInt(), count = 0;
        System.out.print("enter the end ");
        int end = scanner.nextInt();
        Stack<Character> stack = new Stack<>();
        for (int i = start; i <= end; i++) {
            char c = seq.charAt(i);
            if (c == '(')
                stack.push(c);
            else if (stack.pop() == '(')
                count += 2;
        }
        System.out.println(count);
        scanner.close();
    }
}
