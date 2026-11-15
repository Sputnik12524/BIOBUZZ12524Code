package org.firstinspires.ftc.teamcode.input.bindings.TestBindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.input.Binding;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;


public class ShooterBindings extends Binding {
    Robot robot;
    GamepadManager gamepad;

    public ShooterBindings(
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
                () -> new ShooterCommand(robot.sh, Shooter.ShooterStates.SHOOTING_VELO)
        );
        bind(
                gamepad.B,
                BindingMode.TOGGLE,
                () -> new ShooterCommand(robot.sh, Shooter.ShooterStates.SHOOTING_POWER)
        );
    }
}
