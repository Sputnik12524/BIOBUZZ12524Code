package org.firstinspires.ftc.teamcode.modules;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
public class Shooter {

    public final DcMotorEx shooterUpper;
    public final DcMotorEx shooterLower;

    final VoltageSensor batteryVoltage;

    public static double target = 0;

    final double TICKS_PER_REV = 28;
    double RPS = 50;
    double GEAR_RATIO = 1;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0,0,0,0);

    public Shooter(LinearOpMode opMode) {
        shooterUpper = opMode.hardwareMap.get(DcMotorEx.class, "shooterUp");
        shooterLower = opMode.hardwareMap.get(DcMotorEx.class, "shooterDown");

        batteryVoltage = opMode.hardwareMap.voltageSensor.iterator().next();

        shooterLower.setDirection(DcMotorEx.Direction.REVERSE);

        shooterUpper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterLower.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shooterUpper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterLower.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        setPIDFCoefficients(shooterUpper, pidfCoefficients);
        setPIDFCoefficients(shooterLower, pidfCoefficients);
    }

    public void setPIDFCoefficients(DcMotorEx motor, PIDFCoefficients coefficients) {
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d, coefficients.f * 12 / batteryVoltage.getVoltage())
        );
    }

    public void setPower(double power) {
        shooterUpper.setPower(power);
        shooterLower.setPower(power);
    }

    public void setTargetVelo(double newTarget) {
        target = newTarget;
    }

    public void shootByVelo() {
        shooterUpper.setVelocity(target);
        shooterLower.setVelocity(target);
    }
    public void shootStop(){
        shooterUpper.setVelocity(0);
        shooterLower.setVelocity(0);
    }

    public double getVelocityUpper() {
        return  shooterUpper.getVelocity();
    }
    public double getVelocityLower() {
        return shooterLower.getVelocity();
    }
    public double convertRPStoTPS(double rps) {
        return rps*TICKS_PER_REV;
    }
    public double convertTicksToRPS(double ticks_per_rev){
        return ticks_per_rev / TICKS_PER_REV;
    }

}
