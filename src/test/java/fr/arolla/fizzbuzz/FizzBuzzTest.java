package fr.arolla.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void should_return_fizz_for_3() {
        final String result = FizzBuzz.evaluate(3);
        assertThat(result).isEqualTo("Fizz");
    }

    @Test
    public void should_return_fizz_for_5() {
        final String result = FizzBuzz.evaluate(5);
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    public void should_return_fizz_for_10() {
        final String result = FizzBuzz.evaluate(10);
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    public void should_return_fizzbuzz_for_15() {
        final String result = FizzBuzz.evaluate(15);
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    public void should_return_fizzbuzz_for_30() {
        final String result = FizzBuzz.evaluate(30);
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    public void should_return_4_for_4() {
        final String result = FizzBuzz.evaluate(4);
        assertThat(result).isEqualTo("4");
    }

    @Test
    public void should_return_7_for_7() {
        final String result = FizzBuzz.evaluate(7);
        assertThat(result).isEqualTo("7");
    }
}
