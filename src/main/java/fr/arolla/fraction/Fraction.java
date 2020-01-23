package fr.arolla.fraction;

public class Fraction {
    private int numerator;
    private int denominator;

    public class ZeroDenominatorException extends Exception {

    }

    public Fraction(int numerator, int denominator) throws ZeroDenominatorException {
        if(denominator == 0) {
            throw new ZeroDenominatorException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static boolean equal(Fraction f1, Fraction f2) {
        return f1.numerator == f2.numerator && f1.denominator == f2.denominator;
    }

    public static Fraction add(Fraction f1, Fraction f2) throws Fraction.ZeroDenominatorException {
        final Fraction f1bis = f1.times(f2.denominator);
        final Fraction f2bis = f2.times(f1.denominator);
        final int numerator = f1bis.numerator + f2bis.numerator;
        final int denominator = f1.denominator * f2.denominator;
        final int gcd = GCD.get(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    public Fraction times(int times) throws Fraction.ZeroDenominatorException {
        return new Fraction(this.numerator * times, this.denominator * times);
    }
}
