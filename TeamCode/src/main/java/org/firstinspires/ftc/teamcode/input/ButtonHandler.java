package org.firstinspires.ftc.teamcode.input;

public class ButtonHandler {

    private boolean previousState;
    private boolean currentState;
    private long pressedTime;

    public void update(boolean state) {
        previousState = currentState;
        currentState = state;

        if (wasPressed()) {
            pressedTime = System.nanoTime();
        }
    }

    public boolean isDown() {
        return currentState;
    }

    public boolean wasPressed() {
        return currentState && !previousState;
    }

    public boolean wasReleased() {
        return !currentState && previousState;
    }

    public boolean isHeld() {
        return currentState && previousState;
    }

    public long getHoldTimeMillis() {

        if (!currentState || pressedTime == 0) {
            return 0;
        }

        return (System.nanoTime() - pressedTime)
                / 1_000_000L;
    }

    public boolean isHeldFor(long milliseconds) {
        return isDown()
                && getHoldTimeMillis() >= milliseconds;
    }

    public void reset() {
        previousState = false;
        currentState = false;
        pressedTime = 0;
    }
}