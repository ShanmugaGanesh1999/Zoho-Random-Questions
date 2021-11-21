package Set2;

public class numberSystemWith3And4 {
    public static void main(String[] args) {
        int n = 10, b = 0b00;
        for (int i = 0; i < n; i++)
            b++;
        String s = Integer.toBinaryString(b).replaceAll("0", "3").replaceAll("1", "4");
        System.out.println(s);
    }
}
