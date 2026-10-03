package org.firstinspires.ftc.teamcode.input.Bindings;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.command.commands.TransferInCommand;
import org.firstinspires.ftc.teamcode.command.commands.TransferOutCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.GamepadManager;


public class TransferBindings extends Binding {
    Scheduler scheduler;
    Robot robot;
    GamepadManager gamepad;

    public TransferBindings(
            Scheduler scheduler,
            Robot robot,
            GamepadManager gamepad
    ) {
        super(scheduler);
        this.scheduler = scheduler;
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void configure() {
        bind(
                gamepad.A,
                BindingMode.TOGGLE,
                () -> new TransferInCommand(robot.tr)
        );
        bind(
                gamepad.B,
                BindingMode.TOGGLE,
                () -> new TransferOutCommand(robot.tr)
        );
    }
}
