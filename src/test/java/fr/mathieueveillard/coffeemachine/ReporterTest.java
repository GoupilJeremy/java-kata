package fr.mathieueveillard.coffeemachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReporterTest {

    @Test
    public void should_start_with_empty_state() {
        final Reporter reporter = new Reporter();
        assertThat(reporter.getNumberOfOrders(Drink.COFFEE)).isEqualTo(0);
        assertThat(reporter.getTurnover()).isEqualTo(0);
    }

    @Test
    public void should_count_coffees() {
        final Reporter reporter = new Reporter();
        reporter.log(Drink.COFFEE, .6F);
        reporter.log(Drink.COFFEE, .6F);
        assertThat(reporter.getNumberOfOrders(Drink.COFFEE)).isEqualTo(2);
        assertThat(reporter.getTurnover()).isEqualTo(1.2F);
    }

    @Test
    public void should_count_teas() {
        final Reporter reporter = new Reporter();
        reporter.log(Drink.TEA, .4F);
        reporter.log(Drink.TEA, .4F);
        assertThat(reporter.getNumberOfOrders(Drink.TEA)).isEqualTo(2);
        assertThat(reporter.getTurnover()).isEqualTo(.8F);
    }

    @Test
    public void should_count_chocolates() {
        final Reporter reporter = new Reporter();
        reporter.log(Drink.CHOCOLATE, .5F);
        reporter.log(Drink.CHOCOLATE, .5F);
        assertThat(reporter.getNumberOfOrders(Drink.CHOCOLATE)).isEqualTo(2);
        assertThat(reporter.getTurnover()).isEqualTo(1F);
    }
}
