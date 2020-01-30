package fr.mathieueveillard.fraction;

public class GCD {
    public static int get(int a, int b) {
        return b == 0 ? a : get(b, a % b);
    }
}
