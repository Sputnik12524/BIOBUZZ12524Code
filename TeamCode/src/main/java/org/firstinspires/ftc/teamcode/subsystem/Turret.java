package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret implements Subsystem {

    final CRServo ttServo;
    final AnalogInput enc;
    final DigitalChannel magneticSensor;
    public enum STATES {MOVING_BY_LOCALIZATION, MOVING_BY_CAMERA, STOP}
    STATES state = STATES.MOVING_BY_LOCALIZATION;

    double target = 0;
    double error = 0;
    public static double kP = 0.001;

    public static double V_MIN = 0;
    public static double V_MAX = 3.3; //Надо подобрать/затестить через getVoltage

    public static double DEG_TURRET_LEFTMOST = 0;
    public static double DEG_TURRET_RIGHTMOST = 359;

    public static double DEG_SERVO_MIN = 0;
    public static double DEG_SERVO_MAX = 350;

    public static double POS_SERVO_MIN = 0;
    public static double POS_SERVO_MAX = 1;


    public static double GEAR_CONNECT = 142.0 /43;


    boolean limitState = false;

    public Turret(HardwareMap hardwareMap) {
        ttServo = hardwareMap.get(CRServo.class, "turret");
        enc = hardwareMap.get(AnalogInput.class, "encoderAxon");
        magneticSensor = hardwareMap.get(DigitalChannel.class, "magneticSensor");
    }

    public void init() {
        turnByTarget(0);
        setState(STATES.MOVING_BY_LOCALIZATION);
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void update() {
        switch (state) {
            case MOVING_BY_LOCALIZATION:
                error = target - getCurrentTurretDeg();
                double power = error*kP;

                turnInLimit(power);
                break;
            case STOP:
                ttServo.setPower(0);
                break;
        }
    }

    public void stop() {
        setState(STATES.STOP);
    }

    public void turnByTarget(double target) {
        this.target = target;
    }
    public double convertServoPosToTurretDeg(double servoPos) {
        return (servoPos/POS_SERVO_MAX*DEG_SERVO_MAX)*(GEAR_CONNECT);
    }
    public void turnInLimit(double power) {
        double pos = getCurrentTurretDeg();
        if (pos <= DEG_TURRET_LEFTMOST) {
            ttServo.setPower(0);
            limitState = true;
        } else if (pos >= DEG_TURRET_RIGHTMOST) {
            ttServo.setPower(0);
            limitState = true;
        } else {
            ttServo.setPower(power);
            limitState = false;
        }
    }

    public double angleNormalising(double targetNew) {
        double normTarget = targetNew;
        if (targetNew < DEG_TURRET_RIGHTMOST) {
            normTarget = targetNew + 360;
        } else if (targetNew > DEG_TURRET_LEFTMOST) {
            normTarget = targetNew - 360;
        }
        return normTarget;
    }

    public boolean inLimit() {
        return limitState;
    }
    public double getCurrentTurretDeg() {
        return (((enc.getVoltage() / V_MAX) / POS_SERVO_MAX) * DEG_SERVO_MAX) * GEAR_CONNECT;
    }

}
