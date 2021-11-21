package Set2;

import java.util.Stack;

public class removeBalanceParanthesis {
    public static void main(String[] args) {
        String str = "(((ab)";
        // "(((ab)" -> "(ab)"
        // "((abc)((de))" -> "((abc)(de))"
        Stack<Character> paranthesis = new Stack<>();
        String string = "", res = "";
        for (char c : str.toCharArray()) {
            if (c == '(')
                paranthesis.push(c);
            else if (c == ')') {
                if (paranthesis.peek() == '(') {
                    String local = paranthesis.pop() + string + ')';
                    string = "";
                    res += local;
                    if (local.equals("()"))
                        System.out.print("(" + res + ")");
                    else
                        System.out.print(res);
                    res = "";
                }
            } else
                string += c;
        }
    }
}
