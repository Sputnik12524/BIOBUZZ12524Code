package org.firstinspires.ftc.teamcode.archive.modules;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.math.BigDecimal;
import java.math.RoundingMode;

// https://singultech.app/openftc#run-to-position-axon

public class Turret {

    final CRServo turret;
    final AnalogInput encoder;
    final DigitalChannel magneticSensor;

    public static double GEAR_CONNECT = 1; //-

    public static double V_MIN = 0;
    public static double V_MAX = 3.3; //-
    public double vCurrent = 0;

    public static double POS_LEFTMOST = 0;
    public static double POS_RIGHTMOST = 359;

    public static double DEG_MIN = 0;
    public static double DEG_MAX = 359;
    public double degCurrent = 0;
    double target = 0;
    double error = 0;

    boolean stateMagneting = false;
    boolean isInLimits = false;


    public Turret(LinearOpMode opmode){
        turret = opmode.hardwareMap.get(CRServo.class, "turret");
        encoder = opmode.hardwareMap.get(AnalogInput.class, "encoderTurret");
        magneticSensor = opmode.hardwareMap.get(DigitalChannel.class, "magneticSensor");

    }
    public void turnByTarget(double target) {
        this.target = target;
    }
    public void turnStopByPower() {
        turret.setPower(0);
    }
    public double getCurrentDegTurret() {
        return encoder.getVoltage()/V_MAX*DEG_MAX * GEAR_CONNECT;
    }

    public class TurretRegulator extends Thread {
        private final ElapsedTime timer = new ElapsedTime();

        @Override
        public void run() {
            //reset enc
            timer.reset();
            double power;
            double powerP;
            while (!isInterrupted()) {

               error = target - getCurrentDegTurret();

            }
        }

    }
    public void turnInLimits(double power) {
        double pose = getCurrentDegTurret();
        if (isMagneting() && !stateMagneting) {
            //reset enc
        } else if (pose < POS_RIGHTMOST && power < 0) {
            isInLimits = false;
            turnStopByPower();
        } else if (pose > POS_LEFTMOST && power > 0) {
            isInLimits = false;
            turnStopByPower();
        } else {
            isInLimits = true;
            turret.setPower(power);
        }
        stateMagneting = isMagneting();
    }

    public double angleNormalising(double targetNew) {
        double normTarget = targetNew;
        if (targetNew < POS_RIGHTMOST) {
            normTarget = targetNew + 360;
        } else if (targetNew > POS_LEFTMOST) {
            normTarget = targetNew - 360;
        }
        return normTarget;
    }


    public boolean isMagneting() {
        return !magneticSensor.getState();
    }










}
