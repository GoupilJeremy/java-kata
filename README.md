# java-katas

## Leap year

Write a function that returns true or false depending on whether its input is a leap year or not. A leap year is divisible by 4, but is not otherwise divisible by 100, unless it is also divisible by 400.

## Fraction

Write a program that implements the addition of fractions. A Fraction is value object, once created it cannot be mutated. The add method must return a new Fraction instance. Fraction must be reduced.

An implementation of the greatest common divisor (GCD) is provided:
```
static int gcd(int a, int b) { 
  return b == 0 ? a : gcd(b, a % b); 
}
```