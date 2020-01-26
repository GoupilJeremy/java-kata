package fr.arolla.bowling;

import fr.arolla.bowling.capabilities.Frame;

import java.util.ArrayList;

public class LastFrame extends CommonFrame implements Frame {

    private enum FrameEnum {
        REGULAR_FRAME(0),
        ADDITIONAL_FRAME_ONE(1),
        ADDITIONAL_FRAME_TWO(2);

        private int order;

        FrameEnum(int order) {
            this.order = order;
        }
    }

    private final RegularFrame regularFrame;

    private final ArrayList<CommonFrame> frames = new ArrayList();

    private FrameEnum currentFrame = FrameEnum.REGULAR_FRAME;

    public LastFrame() {
        this.regularFrame = new RegularFrame();
        this.frames.add(this.regularFrame);
    }

    @Override
    public void roll(int numberOfPinsKnocked) throws InvalidNumberOfPinsKnockedException, FrameHasAlreadyEndedException {
        this.frames.get(this.currentFrame.order).roll(numberOfPinsKnocked);
        this.next();
    }

    @Override
    public int computeOwnScore() {
        return this.frames.stream().map(CommonFrame::computeOwnScore).mapToInt(Integer::intValue).sum();
    }

    @Override
    public int computeBonus() {
        return 0;
    }

    @Override
    public int computeScoreOfNextRoll() {
        return this.regularFrame.computeScoreOfNextRoll();
    }

    @Override
    public int computeScoreOfNextTwoRolls() {
        if(this.regularFrame.isStrike()) {
            return NUMBER_OF_PINS + this.frames.get(FrameEnum.ADDITIONAL_FRAME_ONE.order).computeOwnScore();
        }
        return this.regularFrame.computeScoreOfNextTwoRolls();
    }

    private void next() {
        if (!this.regularFrame.hasEnded()) {
            return;
        }
        if (this.regularFrame.isSpare()) {
            if(this.currentFrame == FrameEnum.REGULAR_FRAME) {
                this.frames.add(new VirtualFrame());
                this.currentFrame = FrameEnum.ADDITIONAL_FRAME_ONE;
                return;
            }
            if(this.currentFrame == FrameEnum.ADDITIONAL_FRAME_ONE) {
                this.hasEnded = true;
            }
        }
        if (this.regularFrame.isStrike()) {
            if (this.currentFrame == FrameEnum.REGULAR_FRAME) {
                this.frames.add(new VirtualFrame());
                this.currentFrame = FrameEnum.ADDITIONAL_FRAME_ONE;
                return;
            }
            if (this.currentFrame == FrameEnum.ADDITIONAL_FRAME_ONE) {
                this.frames.add(new VirtualFrame());
                this.currentFrame = FrameEnum.ADDITIONAL_FRAME_TWO;
                return;
            }
            if (this.currentFrame == FrameEnum.ADDITIONAL_FRAME_TWO) {
                this.hasEnded = true;
            }
        }
        this.hasEnded = true;
    }
}
