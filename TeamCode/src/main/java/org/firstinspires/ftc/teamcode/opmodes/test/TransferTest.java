package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.input.Bindings.TransferBindings;

import org.firstinspires.ftc.teamcode.input.GamepadManager;


@TeleOp(name = "Transfer Test", group = "test")
public class TransferTest extends LinearOpMode {
    Robot robot;
    GamepadManager gamepad;
    private TransferBindings binding;

    public void runOpMode() {
        robot = new Robot(this);
        gamepad = new GamepadManager(gamepad1);
        binding = new TransferBindings(robot.scheduler, robot, gamepad);
        binding.configure();

        waitForStart();
        while (opModeIsActive()) {

            gamepad.update();
            robot.update();
            binding.update();
        }
    }

}
