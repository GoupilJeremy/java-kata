package fr.arolla.bowling.capabilities;

public interface CanBePlayed {
    class InvalidNumberOfPinsKnockedException extends Exception {
    }
    class FrameHasAlreadyEndedException extends Exception {
    }
    boolean hasEnded();
    void roll(int numberOfPinsKnocked) throws InvalidNumberOfPinsKnockedException, FrameHasAlreadyEndedException;
    int computeOwnScore();
}
