package fr.arolla.tennis;

import java.util.HashMap;

public class Game {

    public enum Player {
        ONE("Player 1"),
        TWO("Player 2");

        private String name;

        Player(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Player other(Player player) {
            if (player == Player.ONE) {
                return Player.TWO;
            }
            return Player.ONE;
        }
    }

    public class GameHasAlreadyEndedException extends Exception {
    }

    private static String[] SCORE_DISPLAY = new String[]{"0", "15", "30", "40"};

    private final HashMap<Player, Integer> scores = new HashMap();

    public Game() {
        this.scores.put(Player.ONE, 0);
        this.scores.put(Player.TWO, 0);
    }

    private static String displayIndividualScore(int score) {
        return SCORE_DISPLAY[score];
    }

    private void incrementScore(Player player) {
        this.scores.replace(player, this.scores.get(player) + 1);
    }

    private boolean isDeuce() {
        return this.scores.get(Player.ONE) == 3 && this.scores.get(Player.TWO) == 3;
    }

    private boolean isDeuceOrLater() {
        return this.scores.get(Player.ONE) >= 3 && this.scores.get(Player.TWO) >= 3;
    }

    private void resetToDeuce() {
        this.scores.replace(Player.ONE, 3);
        this.scores.replace(Player.TWO, 3);
    }

    private boolean playerHasAdvantage(Player player) {
        return this.scores.get(player) == 4 && this.scores.get(Player.other(player)) == 3;
    }

    private boolean otherPlayerHasAdvantage(Player player) {
        return this.playerHasAdvantage(Player.other(player));
    }

    private Player getAdvantagedPlayerIfAny() {
        if (this.playerHasAdvantage(Player.ONE)) {
            return Player.ONE;
        }
        if (this.playerHasAdvantage(Player.TWO)) {
            return Player.TWO;
        }
        return null;
    }

    private boolean playerWins(Player player) {
        return !this.isDeuceOrLater() && this.scores.get(player) == 4 || this.scores.get(player) == 5;
    }

    private Player getWinnerIfAny() {
        if (this.playerWins(Player.ONE)) {
            return Player.ONE;
        }
        if (this.playerWins(Player.TWO)) {
            return Player.TWO;
        }
        return null;
    }

    private boolean gameHasAlreadyEnded() {
        return this.getWinnerIfAny() != null;
    }

    private String displayScoreWithWinner() {
        final Player winner = this.getWinnerIfAny();
        if (winner != null) {
            return winner + " wins";
        }
        return "";
    }

    private String displayScoreWithAdvantage() {
        final Player advantagedPlayer = this.getAdvantagedPlayerIfAny();
        if (advantagedPlayer != null) {
            return "Advantage " + advantagedPlayer;
        }
        return "";
    }

    private String displayScoreAtDeuce() {
        if (this.isDeuce()) {
            return "Deuce";
        }
        return "";
    }

    private String displayRegularScore() {
        return displayIndividualScore(this.scores.get(Player.ONE)) + "-" + displayIndividualScore(this.scores.get(Player.TWO));
    }

    public void score(Player player) throws GameHasAlreadyEndedException {
        if (this.gameHasAlreadyEnded()) {
            throw new GameHasAlreadyEndedException();
        }
        if (this.otherPlayerHasAdvantage(player)) {
            this.resetToDeuce();
            return;
        }
        this.incrementScore(player);
    }

    public String displayScore() {
        final String specificScoreDisplay = this.displayScoreWithWinner() + this.displayScoreWithAdvantage() + this.displayScoreAtDeuce();
        if(specificScoreDisplay.length() > 0) {
            return specificScoreDisplay;
        }
        return this.displayRegularScore();
    }
}
