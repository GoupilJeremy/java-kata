package fr.arolla.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VirtualFrameTest {

    @Test
    public void should_start_a_frame_with_a_score_of_0() {
        final VirtualFrame frame = new VirtualFrame();
        assertThat(frame.computeOwnScore()).isEqualTo(0);
    }

    @Test
    public void should_allow_to_register_a_regular_roll() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final VirtualFrame frame = new VirtualFrame();
        frame.roll(7);
        assertThat(frame.computeOwnScore()).isEqualTo(7);
    }

    @Test
    public void should_throw_an_exception_if_the_number_of_pins_knocked_is_not_valid() {
        try {
            final VirtualFrame frame = new VirtualFrame();
            frame.roll(11);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.InvalidNumberOfPinsKnockedException.class);
        }
    }

    @Test
    public void should_throw_an_exception_if_1_roll_has_already_been_registered() {
        try {
            final VirtualFrame frame = new VirtualFrame();
            frame.roll(7);
            frame.roll(2);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.FrameHasAlreadyEndedException.class);
        }
    }
}
