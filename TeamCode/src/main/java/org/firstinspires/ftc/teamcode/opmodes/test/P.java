package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class P extends LinearOpMode {
    double error;
    double k = 0.5;
    double target = 1000;
    double power;
    private DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "ddd");
        while (opModeIsActive()) {
            motor.getCurrentPosition();
            error = target - motor.getCurrentPosition();
            power = k * error;
            motor.setPower(power);
            telemetry.update();
            telemetry.addData("target", target);
            telemetry.addData("position", motor.getCurrentPosition());
        }
    }
}
