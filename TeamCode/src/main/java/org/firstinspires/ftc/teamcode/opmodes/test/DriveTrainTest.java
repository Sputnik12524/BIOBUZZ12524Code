package org.firstinspires.ftc.teamcode.opmodes.test;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.DriveTrainBindings;

@TeleOp(name = "DriveTrain and blockers test", group = "1")

public class DriveTrainTest extends LinearOpMode {

    Robot robot;
    DriveTrainBindings driveTrainBindings;

    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        driveTrainBindings = new DriveTrainBindings(robot, robot.gamepad1);
        robot.init();
        driveTrainBindings.configure();

        waitForStart();
        while (opModeIsActive()) {
            robot.update();
            driveTrainBindings.update();
        }
    }
}
