package org.firstinspires.ftc.teamcode.input.bindings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.DriveTrainCommand;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;

public class DriveTrainBindings extends Binding {
    Robot robot;
    GamepadManager gamepad;

    public DriveTrainBindings(
            Robot robot,
            GamepadManager gamepad
    ) {
        super(robot.scheduler);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void configure() {
        bind(
                gamepad.X,
                BindingMode.TOGGLE,
                () -> new DriveTrainCommand(robot.dt, DriveTrain.BlockerState.IDLE)
        );
        bind(
                gamepad.Y,
                BindingMode.TOGGLE,
                () -> new DriveTrainCommand(robot.dt, DriveTrain.BlockerState.BLOCKER_FOR_SHOT)
        );

    }

}
