package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.TestBindings.TransferBindings;

@TeleOp(name = "Transfer Test", group = "test")
public class TransferTest extends LinearOpMode {
    Robot robot;
    TransferBindings binding;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        binding = new TransferBindings(robot);
        robot.init();
        binding.configure();
        waitForStart();

        while (opModeIsActive()) {
            robot.update();
            binding.update();
        }
    }
}
