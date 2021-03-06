package fr.mathieueveillard.bowling;

import fr.mathieueveillard.bowling.capabilities.CanBePlayed;

public abstract class CommonFrame implements CanBePlayed {
    protected final static int NUMBER_OF_PINS = 10;
    protected boolean hasEnded = false;
    public boolean hasEnded() {
        return this.hasEnded;
    }
}
