package fr.arolla.bowling;

import fr.arolla.bowling.capabilities.Frame;
import fr.arolla.bowling.capabilities.IsPartOfBonusComputation;

import java.util.HashMap;

public class RegularFrame extends CommonFrame implements Frame {
    private enum Roll {
        ONE,
        TWO,
    }

    private final HashMap<Roll, Integer> scores = new HashMap();

    private Roll roll = Roll.ONE;

    private IsPartOfBonusComputation nextFrame = null;

    public RegularFrame() {
        this.scores.put(Roll.ONE, 0);
        this.scores.put(Roll.TWO, 0);
    }

    @Override
    public void roll(int numberOfPinsKnocked) throws InvalidNumberOfPinsKnockedException, FrameHasAlreadyEndedException {
        if (this.hasEnded) {
            throw new FrameHasAlreadyEndedException();
        }
        if (this.computeOwnScore() + numberOfPinsKnocked > NUMBER_OF_PINS) {
            throw new InvalidNumberOfPinsKnockedException();
        }
        this.scores.replace(this.roll, numberOfPinsKnocked);
        this.next();
    }

    @Override
    public int computeOwnScore() {
        return this.scores.get(Roll.ONE) + this.scores.get(Roll.TWO);
    }

    public void setNextFrame(IsPartOfBonusComputation nextFrame) {
        this.nextFrame = nextFrame;
    }

    @Override
    public int computeBonus() {
        if (this.isSpare() && this.nextFrame != null) {
            return this.nextFrame.computeScoreOfNextRoll();
        }
        if (this.isStrike() && this.nextFrame != null) {
            return nextFrame.computeScoreOfNextTwoRolls();
        }
        return 0;
    }

    @Override
    public int computeScoreOfNextRoll() {
        return this.scores.get(Roll.ONE);
    }

    @Override
    public int computeScoreOfNextTwoRolls() {
        if (this.isStrike()) {
            return NUMBER_OF_PINS + (this.nextFrame != null ? this.nextFrame.computeScoreOfNextRoll() : 0);
        }
        return this.computeOwnScore();
    }

    public boolean isSpare() {
        return this.computeOwnScore() == NUMBER_OF_PINS && this.roll == Roll.TWO;
    }

    public boolean isStrike() {
        return this.computeOwnScore() == NUMBER_OF_PINS && this.roll == Roll.ONE;
    }

    private void next() throws FrameHasAlreadyEndedException {
        if (this.computeOwnScore() == NUMBER_OF_PINS) {
            this.hasEnded = true;
            return;
        }
        if (this.roll == Roll.ONE) {
            this.roll = Roll.TWO;
            return;
        }
        if (this.roll == Roll.TWO) {
            this.hasEnded = true;
            return;
        }
        throw new FrameHasAlreadyEndedException();
    }
}
