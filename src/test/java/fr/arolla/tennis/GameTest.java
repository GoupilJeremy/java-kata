package fr.arolla.tennis;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private void setGameAtDeuceState(Game game) throws Game.GameHasAlreadyEndedException {
        game.score(Game.Player.ONE);
        game.score(Game.Player.TWO);
        game.score(Game.Player.ONE);
        game.score(Game.Player.TWO);
        game.score(Game.Player.ONE);
        game.score(Game.Player.TWO);
    }

    @Test
    public void should_initialize_scores() {
        final Game game = new Game();
        assertThat(game.displayScore()).isEqualTo("0-0");
    }

    @Test
    public void should_allow_to_increment_first_player_score() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("15-0");
    }

    @Test
    public void should_allow_to_increment_second_player_score() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.score(Game.Player.TWO);
        assertThat(game.displayScore()).isEqualTo("0-15");
    }

    @Test
    public void should_display_2_as_30() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("30-0");
    }

    @Test
    public void should_display_3_as_40() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("40-0");
    }

    @Test
    public void should_display_deuce_if_both_players_have_40() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        assertThat(game.displayScore()).isEqualTo("Deuce");
    }

    @Test
    public void should_display_advantage_player_1_if_first_player_scores_after_deuce() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("Advantage Player 1");
    }

    @Test
    public void should_display_advantage_player_2_if_second_player_scores_after_deuce() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        game.score(Game.Player.TWO);
        assertThat(game.displayScore()).isEqualTo("Advantage Player 2");
    }

    @Test
    public void starting_from_advantage_should_reset_to_deuce_if_the_player_without_advantage_scores() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        game.score(Game.Player.ONE);
        game.score(Game.Player.TWO);
        assertThat(game.displayScore()).isEqualTo("Deuce");
    }

    @Test
    public void starting_from_advantage_the_player_with_advantage_wins_if_he_scores() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("Player 1 wins");
    }

    @Test
    public void should_throw_an_exception_if_a_player_scores_after_the_game_is_ended() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        this.setGameAtDeuceState(game);
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        try {
            game.score(Game.Player.ONE);
            throw new Exception("Red test");
        } catch (Exception exception) {
            assertThat(exception).isExactlyInstanceOf(Game.GameHasAlreadyEndedException.class);
        }
    }

    @Test
    public void not_starting_from_deuce_state_a_player_at_40_should_win_if_he_scores() throws Game.GameHasAlreadyEndedException {
        final Game game = new Game();
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        game.score(Game.Player.ONE);
        assertThat(game.displayScore()).isEqualTo("Player 1 wins");
    }
}
