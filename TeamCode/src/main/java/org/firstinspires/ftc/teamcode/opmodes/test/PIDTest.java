package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class PIDTest extends LinearOpMode {
    double error;
    double k = 0.5;
    public static double target = 25;
    double power;
    private DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
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
