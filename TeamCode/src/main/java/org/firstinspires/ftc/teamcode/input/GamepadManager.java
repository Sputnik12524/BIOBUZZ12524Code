package org.firstinspires.ftc.teamcode.input;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadManager {

    private final Gamepad rawGamepad;

    public final ButtonHandler A = new ButtonHandler();
    public final ButtonHandler B = new ButtonHandler();
    public final ButtonHandler X = new ButtonHandler();
    public final ButtonHandler Y = new ButtonHandler();

    public final ButtonHandler leftBumper = new ButtonHandler();
    public final ButtonHandler rightBumper = new ButtonHandler();

    public final ButtonHandler leftStickButton =
            new ButtonHandler();

    public final ButtonHandler rightStickButton =
            new ButtonHandler();

    public final ButtonHandler dpadUp =
            new ButtonHandler();

    public final ButtonHandler dpadDown =
            new ButtonHandler();

    public final ButtonHandler dpadLeft =
            new ButtonHandler();

    public final ButtonHandler dpadRight =
            new ButtonHandler();

    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private double rightStickY;

    private double leftTrigger;
    private double rightTrigger;

    public GamepadManager(Gamepad gamepad) {

        if (gamepad == null) {
            throw new IllegalArgumentException(
                    "Gamepad cannot be null"
            );
        }

        this.rawGamepad = gamepad;
    }

    public void update() {

        A.update(rawGamepad.a);
        B.update(rawGamepad.b);
        X.update(rawGamepad.x);
        Y.update(rawGamepad.y);

        leftBumper.update(rawGamepad.left_bumper);
        rightBumper.update(rawGamepad.right_bumper);

        leftStickButton.update(
                rawGamepad.left_stick_button
        );

        rightStickButton.update(
                rawGamepad.right_stick_button
        );

        dpadUp.update(rawGamepad.dpad_up);
        dpadDown.update(rawGamepad.dpad_down);
        dpadLeft.update(rawGamepad.dpad_left);
        dpadRight.update(rawGamepad.dpad_right);

        leftStickX = rawGamepad.left_stick_x;
        leftStickY = rawGamepad.left_stick_y;

        rightStickX = rawGamepad.right_stick_x;
        rightStickY = rawGamepad.right_stick_y;

        leftTrigger = rawGamepad.left_trigger;
        rightTrigger = rawGamepad.right_trigger;
    }

    public double leftStickX() {
        return leftStickX;
    }

    public double leftStickY() {
        return leftStickY;
    }

    public double rightStickX() {
        return rightStickX;
    }

    public double rightStickY() {
        return rightStickY;
    }

    public double leftTrigger() {
        return leftTrigger;
    }

    public double rightTrigger() {return rightTrigger;}

    public void reset() {
        A.reset();
        B.reset();
        X.reset();
        Y.reset();

        leftBumper.reset();
        rightBumper.reset();

        leftStickButton.reset();
        rightStickButton.reset();

        dpadUp.reset();
        dpadDown.reset();
        dpadLeft.reset();
        dpadRight.reset();
    }
}