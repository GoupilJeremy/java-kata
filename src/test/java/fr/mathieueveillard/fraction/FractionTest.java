package fr.mathieueveillard.fraction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FractionTest {

    @Test
    public void should_throw_if_denominator_equals_0() {
        try {
            final Fraction f = new Fraction(1, 0);
            throw new Exception("Red test");
        } catch(Exception exception) {
            assertThat(exception).isExactlyInstanceOf(Fraction.ZeroDenominatorException.class);
        }
    }

    @Test
    public void two_fractions_differ_if_numerators_differ() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(1, 1);
        final Fraction f2 = new Fraction(2, 1);
        assertThat(Fraction.equal(f1, f2)).isEqualTo(false);
    }

    @Test
    public void two_fractions_differ_if_denominators_differ() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(1, 1);
        final Fraction f2 = new Fraction(1, 2);
        assertThat(Fraction.equal(f1, f2)).isEqualTo(false);
    }

    @Test
    public void a_fraction_equals_itself() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(1, 2);
        final Fraction f2 = new Fraction(1, 2);
        assertThat(Fraction.equal(f1, f2)).isEqualTo(true);
    }

    @Test
    public void should_multiply_a_fraction() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(1, 2);
        final Fraction actual = f1.times(2);
        final Fraction expected = new Fraction(2, 4);
        assertThat(Fraction.equal(actual, expected)).isEqualTo(true);
    }

    @Test
    public void should_add_two_fractions_without_reduction() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(2, 1);
        final Fraction f2 = new Fraction(3, 2);
        final Fraction actual = Fraction.add(f1, f2);
        final Fraction expected = new Fraction(7, 2);
        assertThat(Fraction.equal(actual, expected)).isEqualTo(true);
    }

    @Test
    public void should_add_another_two_fractions_without_reduction() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(2, 3);
        final Fraction f2 = new Fraction(3, 2);
        final Fraction actual = Fraction.add(f1, f2);
        final Fraction expected = new Fraction(13, 6);
        assertThat(Fraction.equal(actual, expected)).isEqualTo(true);
    }

    @Test
    public void should_add_two_fractions_with_reduction() throws Fraction.ZeroDenominatorException {
        final Fraction f1 = new Fraction(2, 3);
        final Fraction f2 = new Fraction(2, 3);
        final Fraction actual = Fraction.add(f1, f2);
        final Fraction expected = new Fraction(4, 3);
        assertThat(Fraction.equal(actual, expected)).isEqualTo(true);
    }
}
