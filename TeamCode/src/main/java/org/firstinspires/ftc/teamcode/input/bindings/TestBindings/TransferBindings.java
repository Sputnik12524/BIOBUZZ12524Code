package org.firstinspires.ftc.teamcode.input.bindings.TestBindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.TransferCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.Binding;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

public class TransferBindings extends Binding {
    Robot robot;

    public TransferBindings(Robot robot) {
        super(robot.scheduler);
        this.robot = robot;
    }

    public void configure() {
        bind(robot.gamepad1.A, BindingMode.TOGGLE,
                () -> new TransferCommand(robot.tr, Transfer.STATES.IN));
        bind(robot.gamepad1.A, BindingMode.TOGGLE,
                () -> new TransferCommand(robot.tr, Transfer.STATES.OUT));
    }

}
