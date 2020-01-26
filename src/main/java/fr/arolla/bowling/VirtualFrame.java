package fr.arolla.bowling;

public class VirtualFrame extends CommonFrame {

    private int score = 0;

    @Override
    public void roll(int numberOfPinsKnocked) throws InvalidNumberOfPinsKnockedException, FrameHasAlreadyEndedException {
        if (this.hasEnded) {
            throw new FrameHasAlreadyEndedException();
        }
        if (numberOfPinsKnocked > NUMBER_OF_PINS) {
            throw new InvalidNumberOfPinsKnockedException();
        }
        this.score = numberOfPinsKnocked;
        this.next();
    }

    @Override
    public int computeOwnScore() {
        return this.score;
    }

    private void next() throws FrameHasAlreadyEndedException {
        if (!this.hasEnded) {
            this.hasEnded = true;
            return;
        }
        throw new FrameHasAlreadyEndedException();
    }
}
