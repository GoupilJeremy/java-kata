package fr.mathieueveillard.leapyear;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LeapYearTest {

    @Test
    public void _2004_is_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2004);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void _2005_is_not_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2005);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void _2008_is_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2008);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void _2100_is_not_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2100);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void _2200_is_not_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2200);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void _2000_is_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2000);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void _2400_is_a_leap_year() {
        final boolean result = LeapYear.isLeapYear(2400);
        assertThat(result).isEqualTo(true);
    }
}
