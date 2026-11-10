package org.firstinspires.ftc.teamcode.input.bindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.Intake;


public class IntakeBindings extends Binding {
    Robot robot;
    GamepadManager gamepad;

    public IntakeBindings(
            Robot robot,
            GamepadManager gamepad
    ) {
        super(robot.scheduler);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void configure() {
        bind(
                gamepad.A,
                BindingMode.TOGGLE,
                () -> new IntakeCommand(robot.in, Intake.STATES.IN)
        );
        bind(
                gamepad.B,
                BindingMode.TOGGLE,
                () -> new IntakeCommand(robot.in, Intake.STATES.OUT)
        );
    }
}
