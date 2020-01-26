package fr.arolla.bowling;

import fr.arolla.bowling.capabilities.Frame;

import java.util.ArrayList;

public class Game {
    public static class GameHasAlreadyEndedException extends Exception {
    }

    private final static int NUMBER_OF_FRAMES = 10;

    private ArrayList<Frame> frames = new ArrayList();

    public Game() {
        this.frames.add(new RegularFrame());
    }

    public void roll(int numberOfPinsKnocked) throws RegularFrame.InvalidNumberOfPinsKnockedException, RegularFrame.FrameHasAlreadyEndedException, GameHasAlreadyEndedException {
        if (this.hasEnded()) {
            throw new GameHasAlreadyEndedException();
        }
        if (this.getLastFrame().hasEnded()) {
            this.addFrame();
        }
        this.getLastFrame().roll(numberOfPinsKnocked);
    }

    public int getNumberOfFrames() {
        return this.frames.size();
    }

    public boolean hasEnded() {
        return this.getNumberOfFrames() == NUMBER_OF_FRAMES && this.getLastFrame().hasEnded();
    }

    public int computeScore() {
        final int sumOfScores = this.frames.stream().map(Frame::computeOwnScore).mapToInt(Integer::intValue).sum();
        final int sumOfBonus = this.frames.stream().map(Frame::computeBonus).mapToInt(Integer::intValue).sum();
        return sumOfScores + sumOfBonus;
    }

    private Frame getLastFrame() {
        return this.frames.get(this.getNumberOfFrames() - 1);
    }

    private Frame buildNewFrame() {
        if (this.getNumberOfFrames() == NUMBER_OF_FRAMES - 1) {
            return new LastFrame();
        }
        return new RegularFrame();
    }

    private void addFrame() {
        Frame currentFrame = this.getLastFrame();
        Frame newFrame = this.buildNewFrame();
        ((RegularFrame) currentFrame).setNextFrame(newFrame);
        this.frames.add(newFrame);
    }
}
