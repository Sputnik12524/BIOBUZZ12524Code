package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;

@Autonomous(name = "AutoPark", group = "0")

public class AutoPark extends LinearOpMode {
    DriveTrain dt;
    Robot robot;
    static final double DISTANCE = 5;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        dt = new DriveTrain(hardwareMap, robot.eventBus);
        dt.init();
        waitForStart();
        while (opModeIsActive()) {
            dt.moveForwardByCenti(DISTANCE);
        }
    }
}

