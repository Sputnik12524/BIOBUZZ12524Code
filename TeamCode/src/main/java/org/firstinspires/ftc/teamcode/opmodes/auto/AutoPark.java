package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;

@Autonomous(name = "AutoPark", group = "0")

public class AutoPark extends LinearOpMode {
    DriveTrain dt;
    static double DISTANCE = 5;

    @Override
    public void runOpMode() {
        dt = new DriveTrain(hardwareMap);
        dt.init();
        waitForStart();
        while (opModeIsActive()) {
            dt.moveForwardByCenti(DISTANCE);
        }
    }
}

