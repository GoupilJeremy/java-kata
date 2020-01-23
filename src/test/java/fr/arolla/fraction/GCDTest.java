package fr.arolla.fraction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GCDTest {
    @Test
    public void GCD_of_3_and_8_is_1_because_3_is_prime() {
        assertThat(GCD.get(3,8)).isEqualTo(1);
    }

    @Test
    public void GCD_of_3_and_15_is_3_because_15_is_multiple_of_3() {
        assertThat(GCD.get(3,15)).isEqualTo(3);
    }

    @Test
    public void GCD_of_6_and_9_is_3() {
        assertThat(GCD.get(6,9)).isEqualTo(3);
    }
}
