package org.firstinspires.ftc.teamcode.command.commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DriveByGamepadCommand implements Command {
    DriveTrain dt;
    GamepadManager gamepad1;
    Set<Subsystem> set = new HashSet<>();

    public DriveByGamepadCommand(Robot robot) {
        this.dt = robot.dt;
        gamepad1 = robot.gamepad1;
        set = Set.of(dt);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public void execute() {
        dt.setState(DriveTrain.DriveState.DRIVE);
        dt.setNewTargetPower(-gamepad1.leftStickY(), gamepad1.leftStickX(), (gamepad1.rightTrigger() - gamepad1.leftTrigger()));
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return set;
    }
}
