package org.firstinspires.ftc.teamcode.input.bindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.command.commands.IntakePollenCommand;
import org.firstinspires.ftc.teamcode.command.commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.input.Binding;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;

public class TeleOpBindings extends Binding {
    Robot robot;

    public TeleOpBindings(Robot robot) {
        super(robot.scheduler);
        this.robot = robot;
    }

    public void configure() {
        bind(robot.gamepad1.A, BindingMode.TOGGLE,
                () -> new IntakePollenCommand(robot));

        bind(robot.gamepad1.X, BindingMode.TOGGLE,
                () -> new ShooterCommand(robot.sh, Shooter.ShooterStates.SHOOTING_VELO));

    }
}
