package fr.mathieueveillard.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {

    @Test
    public void should_throw_an_exception_if_2_rolls_have_already_been_registered() {
        try {
            final LastFrame frame = new LastFrame();
            frame.roll(5);
            frame.roll(2);
            frame.roll(2);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.FrameHasAlreadyEndedException.class);
        }
    }

    @Test
    public void should_allow_an_additional_roll_in_case_of_a_spare_and_compute_own_score_accordingly() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(7);
        frame.roll(3);
        frame.roll(6);
        assertThat(frame.computeOwnScore()).isEqualTo(16);
    }

    @Test
    public void should_allow_two_additional_rolls_in_case_of_a_strike_and_compute_own_score_accordingly() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(10);
        frame.roll(6);
        frame.roll(7);
        assertThat(frame.computeOwnScore()).isEqualTo(23);
    }

    @Test
    public void should_return_0_as_bonus() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(6);
        assertThat(frame.computeBonus()).isEqualTo(0);
    }

    @Test
    public void computeScoreOfNextRoll_should_return_the_first_roll_of_the_regular_frame() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(6);
        frame.roll(2);
        assertThat(frame.computeScoreOfNextRoll()).isEqualTo(6);
    }

    @Test
    public void computeScoreOfNextTwoRolls_should_return_the_two_rolls_of_the_regular_frame_if_it_is_not_a_strike() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(6);
        frame.roll(2);
        assertThat(frame.computeScoreOfNextTwoRolls()).isEqualTo(8);
    }

    @Test
    public void computeScoreOfNextTwoRolls_should_return_the_the_first_roll_of_the_regular_frame_if_it_is_a_strike_and_the_next_additional_roll() throws CommonFrame.InvalidNumberOfPinsKnockedException, CommonFrame.FrameHasAlreadyEndedException {
        final LastFrame frame = new LastFrame();
        frame.roll(10);
        frame.roll(2);
        assertThat(frame.computeScoreOfNextTwoRolls()).isEqualTo(12);
    }
}
