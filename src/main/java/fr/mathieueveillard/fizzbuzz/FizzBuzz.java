package fr.mathieueveillard.fizzbuzz;

public class FizzBuzz {

    public static String evaluate(int n) {
        String result = "";
        if (isMultipleOf(3, n)) {
            result = result + "Fizz";
        }
        if (isMultipleOf(5, n)) {
            result = result + "Buzz";
        }
        if(result == "") {
            return Integer.toString(n);
        }
        return result;
    }

    private static boolean isMultipleOf(int modulo, int n) {
        return n % modulo == 0;
    }
}
