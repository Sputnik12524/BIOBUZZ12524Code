package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
public class Shooter implements Subsystem{

    public enum ShooterStates {IDLE, SHOOTING_POWER, SHOOTING_VELO}
    public final DcMotorEx shooterRight;
   // public final DcMotorEx shooterLeft;
    public final Servo angleAdjuster;

    final VoltageSensor batteryVoltage;

    public static double targetVelo = 0;
    public static double adjPose = 0;
    public static double POWER = 0.725;
    public static double VELOCITY = 52;

    final double TICKS_PER_REV = 28;
    double RPS = 50;
    double GEAR_RATIO = 1;

    public static double POS_MAX = 0.54;
    public static double POS_MIN = 0.17;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(1.4,0,0,14.14);

    public ShooterStates state = ShooterStates.IDLE;

    public Shooter(HardwareMap hardwareMap) {
        shooterRight = hardwareMap.get(DcMotorEx.class, "shooterUp");
        angleAdjuster = hardwareMap.get(Servo.class, "adjuster");

        batteryVoltage = hardwareMap.voltageSensor.iterator().next();
    }
    @Override
    public void init() {
        shooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setPIDFCoefficients(shooterRight, pidfCoefficients);

        setState(ShooterStates.IDLE);
    }
    public void setState(ShooterStates state) {
        this.state = state;
    }

    @Override
    public void update() {
        switch (state) {
            case IDLE:
//                setPower(0);
                setTargetVelo(0);
                setAdjusterPose(adjPose);
                break;
            case SHOOTING_POWER:
                setPower(POWER);
                setAdjusterPose(adjPose);
                break;
            case SHOOTING_VELO:
                setTargetVelo(VELOCITY);
                shootByVelo();
                setAdjusterPose(adjPose);
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

    public void setAdjusterPose(double pose) {
        angleAdjuster.setPosition(pose);
    }

    public void setPower(double power) {
        shooterRight.setPower(power);
    }

    public void setTargetVelo(double newTarget) {
        targetVelo = newTarget;
    }

    public void shootByVelo() {
        shooterRight.setVelocity(targetVelo);
    }

    public double getVelocityUpper() {
        return  shooterRight.getVelocity();
    }
    public double getVelocityLower() {
        return 0.0;
    }
    public double getPower(){return shooterRight.getPower();}
    public ShooterStates getState() {
        return state;
    }
    public double getAdjusterPose() {
        return angleAdjuster.getPosition();
    }
    public double convertRPStoTPS(double rps) {
        return rps*TICKS_PER_REV;
    }
    public double convertTicksToRPS(double ticks_per_rev){
        return ticks_per_rev / TICKS_PER_REV;
    }

}