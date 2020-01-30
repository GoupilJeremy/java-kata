package fr.mathieueveillard.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void should_start_game_with_a_single_frame() {
        final Game game = new Game();
        assertThat(game.getNumberOfFrames()).isEqualTo(1);
    }

    @Test
    public void should_create_new_frames_all_along_the_game() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.roll(7);
        game.roll(2);
        game.roll(5);
        assertThat(game.getNumberOfFrames()).isEqualTo(2);
    }

    @Test
    public void should_end_the_game_when_10_frames_have_been_played() throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        assertThat(game.hasEnded()).isEqualTo(false);
        game.roll(7);
        assertThat(game.hasEnded()).isEqualTo(false);
        game.roll(2);
        assertThat(game.hasEnded()).isEqualTo(true);
    }

    @Test
    public void should_throw_an_exception_if_the_user_attempts_to_play_after_the_game_has_ended() {
        try {
            final Game game = new Game();
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(10);
            game.roll(5);
            game.roll(3);
            game.roll(7);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(Game.GameHasAlreadyEndedException.class);
        }
    }

    @Test
    public void should_compute_score() throws RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException, RegularFrame.InvalidNumberOfPinsKnockedException {
        final Game game = new Game();
        game.roll(7);
        game.roll(2);
        game.roll(5);
        assertThat(game.computeScore()).isEqualTo(14);
    }

    @Test
    public void should_compute_score_for_a_spare() throws RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException, RegularFrame.InvalidNumberOfPinsKnockedException {
        final Game game = new Game();
        game.roll(7);
        game.roll(3);
        game.roll(5);
        assertThat(game.computeScore()).isEqualTo(20);
    }

    @Test
    public void should_allow_the_perfect_score() throws RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException, RegularFrame.InvalidNumberOfPinsKnockedException {
        final Game game = new Game();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        assertThat(game.computeScore()).isEqualTo(300);
    }

    @Test
    public void control() throws RegularFrame.FrameHasAlreadyEndedException, Game.GameHasAlreadyEndedException, RegularFrame.InvalidNumberOfPinsKnockedException {
        final Game game = new Game();
        game.roll(10);
        game.roll(6);
        game.roll(1);
        game.roll(5);
        game.roll(5);
        game.roll(7);
        game.roll(3);
        game.roll(10);
        game.roll(10);
        game.roll(8);
        game.roll(2);
        game.roll(8);
        game.roll(1);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        assertThat(game.computeScore()).isEqualTo(196);
    }
}
