package org.firstinspires.ftc.teamcode.input.bindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.TransferCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;


public class TransferBindings extends Binding {
    Robot robot;
    GamepadManager gamepad;

    public TransferBindings(
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
                () -> new TransferCommand(robot.tr, Transfer.STATES.IN)
        );
        bind(
                gamepad.B,
                BindingMode.TOGGLE,
                () -> new TransferCommand(robot.tr, Transfer.STATES.OUT)
        );
    }
}
