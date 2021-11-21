public class countNumberByFinger {
    public static void main(String[] args) {
        int n = 17, mod = n % 8; // pattern repeats in every 8, [1-8, 9-16, 17-24, ...]
        if (mod == 0)
            System.out.println(2);
        else if (mod < 5)
            System.out.println(mod);
        else
            System.out.println(10 - mod);
    }
}
