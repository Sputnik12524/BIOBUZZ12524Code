package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.TestBindings.TransferBindings;

public class TeleOp extends LinearOpMode {
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
