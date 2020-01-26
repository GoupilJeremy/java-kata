package fr.arolla.bowling;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class RegularFrameTest {

    @Test
    public void should_start_a_frame_with_a_score_of_0() {
        final RegularFrame frame = new RegularFrame();
        assertThat(frame.computeOwnScore()).isEqualTo(0);
    }

    @Test
    public void should_allow_to_register_a_regular_roll() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame frame = new RegularFrame();
        frame.roll(7);
        assertThat(frame.computeOwnScore()).isEqualTo(7);
    }

    @Test
    public void should_compute_the_score() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame frame = new RegularFrame();
        frame.roll(7);
        frame.roll(2);
        assertThat(frame.computeOwnScore()).isEqualTo(9);
    }

    @Test
    public void should_throw_an_exception_if_the_number_of_pins_knocked_is_not_valid() {
        try {
            final RegularFrame frame = new RegularFrame();
            frame.roll(11);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.InvalidNumberOfPinsKnockedException.class);
        }
    }

    @Test
    public void should_throw_an_exception_if_the_total_number_of_pins_knocked_is_not_valid() {
        try {
            final RegularFrame frame = new RegularFrame();
            frame.roll(9);
            frame.roll(2);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.InvalidNumberOfPinsKnockedException.class);
        }
    }

    @Test
    public void should_throw_an_exception_if_2_rolls_have_already_been_registered() {
        try {
            final RegularFrame frame = new RegularFrame();
            frame.roll(5);
            frame.roll(2);
            frame.roll(2);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.FrameHasAlreadyEndedException.class);
        }
    }

    @Test
    public void should_end_the_frame_in_case_of_a_strike() {
        try {
            final RegularFrame frame = new RegularFrame();
            frame.roll(10);
            frame.roll(1);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(RegularFrame.FrameHasAlreadyEndedException.class);
        }
    }

    @Test
    public void should_indicate_if_the_frame_has_ended() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame frame = new RegularFrame();
        assertThat(frame.hasEnded()).isEqualTo(false);
        frame.roll(10);
        assertThat(frame.hasEnded()).isEqualTo(true);
    }

    @Test
    public void should_compute_bonus_when_neither_spare_nor_strike() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(7);
        f0.roll(2);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(4);
        f1.roll(2);
        f0.setNextFrame(f1);
        assertThat(f0.computeBonus()).isEqualTo(0);
    }

    @Test
    public void should_compute_bonus_when_spare() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(7);
        f0.roll(3);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(4);
        f1.roll(2);
        f0.setNextFrame(f1);
        assertThat(f0.computeBonus()).isEqualTo(4);
    }

    @Test
    public void should_compute_bonus_when_another_spare() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(7);
        f0.roll(3);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(5);
        f1.roll(2);
        f0.setNextFrame(f1);
        assertThat(f0.computeBonus()).isEqualTo(5);
    }

    @Test
    public void should_compute_bonus_when_strike() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(10);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(5);
        f1.roll(2);
        f0.setNextFrame(f1);
        assertThat(f0.computeBonus()).isEqualTo(7);
    }

    @Test
    public void should_compute_bonus_when_another_strike() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(10);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(5);
        f1.roll(3);
        f0.setNextFrame(f1);
        assertThat(f0.computeBonus()).isEqualTo(8);
    }

    @Test
    public void should_compute_bonus_when_strike_and_next_is_also_a_strike() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException {
        final RegularFrame f0 = new RegularFrame();
        f0.roll(10);
        final RegularFrame f1 = new RegularFrame();
        f1.roll(10);
        final RegularFrame f2 = new RegularFrame();
        f2.roll(7);
        f0.setNextFrame(f1);
        f1.setNextFrame(f2);
        assertThat(f0.computeBonus()).isEqualTo(17);
    }
}
