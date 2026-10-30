package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
public class Shooter implements Subsystem{

    public enum ShooterStates {IDLE, SHOOTING_POWER, SHOOTING_VELO}
    public final DcMotorEx shooterUpper;
    public final DcMotorEx shooterLower;

    final VoltageSensor batteryVoltage;

    public static double targetVelo = 0;
    public static double POWER = 1;

    final double TICKS_PER_REV = 28;
    double RPS = 50;
    double GEAR_RATIO = 1;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(1,0,0,1);

    public ShooterStates state = ShooterStates.IDLE;

    public Shooter(HardwareMap hardwareMap) {
        shooterUpper = hardwareMap.get(DcMotorEx.class, "shooterUp");
        shooterLower = hardwareMap.get(DcMotorEx.class, "shooterDown");

        batteryVoltage = hardwareMap.voltageSensor.iterator().next();

        shooterLower.setDirection(DcMotorEx.Direction.REVERSE);
    }
    @Override
    public void init() {
        shooterUpper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterLower.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shooterUpper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterLower.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

       // setPIDFCoefficients(shooterUpper, pidfCoefficients);
       // setPIDFCoefficients(shooterLower, pidfCoefficients);

        setState(ShooterStates.IDLE);
    }
    public void setState(ShooterStates state) {
        this.state = state;
    }

    @Override
    public void update() {
        switch (state) {
            case IDLE:
                shooterUpper.setPower(0);
                shooterLower.setPower(0);
                break;
            case SHOOTING_POWER:
                setPower(POWER);
                break;
            case SHOOTING_VELO:
                shootByVelo();
                break;
        }
    }

    @Override
    public void stop() {
        setState(ShooterStates.IDLE);
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
        targetVelo = newTarget;
    }

    public void shootByVelo() {
        shooterUpper.setVelocity(targetVelo);
        shooterLower.setVelocity(targetVelo);
    }

    public double getVelocityUpper() {
        return  shooterUpper.getVelocity();
    }
    public double getVelocityLower() {
        return shooterLower.getVelocity();
    }
    public double getPower(){return shooterUpper.getPower();}
    public ShooterStates getState() {
        return state;
    }
    public double convertRPStoTPS(double rps) {
        return rps*TICKS_PER_REV;
    }
    public double convertTicksToRPS(double ticks_per_rev){
        return ticks_per_rev / TICKS_PER_REV;
    }

}