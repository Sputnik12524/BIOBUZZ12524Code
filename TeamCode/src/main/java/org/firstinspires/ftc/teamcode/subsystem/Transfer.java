package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import javax.crypto.spec.OAEPParameterSpec;

public class Transfer implements Subsystem {
    private final double POWER_IN = -1;
    private final double POWER_OUT = -1;
    private final double OPEN = 1;
    private final double CLOSE = -1;

    public enum STATES {IDLE, IN, OUT, SHOOTING}

    STATES state = STATES.IDLE;

    private final DcMotorEx motor;
    private final DcMotorEx motorExtra;
    private final Servo servo;
//    private final CRServo servoLeft;
//    private final CRServo servoRight;

    public Transfer(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "transfer");
        motorExtra = hardwareMap.get(DcMotorEx.class, "motorExtra");
        servo = hardwareMap.get(Servo.class, "transfer_servo");
//        servoLeft = hardwareMap.get(CRServo.class, "transfer_servoL");
//        servoRight = hardwareMap.get(CRServo.class, "transfer_servoR");
//
//        servoRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void init() {
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(STATES.IDLE);
        closeServo();
    }

    @Override
    public void update() {
        switch (state) {
            case IDLE:
                motor.setPower(0);
                motorExtra.setPower(0);
//                servoLeft.setPower(0);
//                servoRight.setPower(0);
                closeServo();
                break;
            case IN:
                motor.setPower(POWER_IN);
                motorExtra.setPower(0);
//                servoRight.setPower(0);
//                servoLeft.setPower(0);
                openServo();
                break;
            case OUT:
                motor.setPower(POWER_OUT);
                motorExtra.setPower(0);
//                servoRight.setPower(0);
//                servoLeft.setPower(0);
                openServo();
                break;
            case SHOOTING:
                motor.setPower(POWER_IN);
                motorExtra.setPower(POWER_IN);
//                servoRight.setPower(POWER_IN);
//                servoLeft.setPower(POWER_IN);
                openServo();
                break;
        }

    }

    @Override
    public void stop() {
        setState(STATES.IDLE);
        motor.setPower(0);
        motorExtra.setPower(0);
//        servoLeft.setPower(0);
//        servoRight.setPower(0);
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void closeServo() {
        servo.setPosition(CLOSE);
    }

    public void openServo() {
        servo.setPosition(OPEN);
    }
}
