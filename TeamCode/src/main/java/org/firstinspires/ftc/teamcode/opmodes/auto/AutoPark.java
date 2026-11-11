package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;

@Autonomous(name = "AutoPark", group = "0")

public class AutoPark extends LinearOpMode {
    DriveTrain dt;
    static double DISTANCE = 40;
    static double POWER = 0.7;
    static final double PULSES = 537.7;
    static final double WHEEL_DIAMETR = 9.6;
    static final double PULSES_PER_CM = PULSES / (Math.PI * WHEEL_DIAMETR);

    @Override
    public void runOpMode() {
        dt = new DriveTrain(hardwareMap);
        dt.init();

        waitForStart();
        while (opModeIsActive()) {

            while (dt.leftBack.getCurrentPosition() <= DISTANCE * PULSES_PER_CM) {
                dt.leftFront.setPower(POWER);
                dt.leftBack.setPower(POWER);
                dt.rightFront.setPower(POWER);
                dt.rightBack.setPower(POWER);
            }
            dt.setState(DriveTrain.BlockerState.IDLE);
        }
    }
}

