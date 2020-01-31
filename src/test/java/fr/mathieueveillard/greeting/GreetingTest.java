package fr.mathieueveillard.greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingTest {

    @Test
    public void should_greet_the_person() {
        final String result = Greeting.greet(new String[] {"Bob"});
        assertThat(result).isEqualTo("Hello, Bob.");
    }

    @Test
    public void should_greet_another_person() {
        final String result = Greeting.greet(new String[] {"Alice"});
        assertThat(result).isEqualTo("Hello, Alice.");
    }

    @Test
    public void should_handle_the_absence_of_a_name() {
        final String result = Greeting.greet(new String[] {null});
        assertThat(result).isEqualTo("Hello, my friend.");
    }

    @Test
    public void should_handle_shouting() {
        final String result = Greeting.greet(new String[] {"BOB"});
        assertThat(result).isEqualTo("HELLO BOB!");
    }

    @Test
    public void should_handle_two_names() {
        final String result = Greeting.greet(new String[] {"Bob", "Alice"});
        assertThat(result).isEqualTo("Hello, Bob and Alice.");
    }

    @Test
    public void should_handle_an_arbitrary_number_of_names() {
        final String result = Greeting.greet(new String[] {"Bob", "Alice", "Charlotte"});
        assertThat(result).isEqualTo("Hello, Bob, Alice, and Charlotte.");
    }

    @Test
    public void should_handle_normal_and_shouted_names() {
        final String result = Greeting.greet(new String[] {"Bob", "ALICE", "Charlotte"});
        assertThat(result).isEqualTo("Hello, Bob and Charlotte. AND HELLO ALICE!");
    }

    @Test
    public void should_split_names_containing_a_comma() {
        final String result = Greeting.greet(new String[] {"Bob", "Charlie, Diane"});
        assertThat(result).isEqualTo("Hello, Bob, Charlie, and Diane.");
    }

    @Test
    public void should_allow_intentional_commas() {
        final String result = Greeting.greet(new String[] {"Bob", "\"Charlie, Diane\""});
        assertThat(result).isEqualTo("Hello, Bob and Charlie, Diane.");
    }
}
