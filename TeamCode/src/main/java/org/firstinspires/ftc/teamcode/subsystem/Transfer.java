package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import javax.crypto.spec.OAEPParameterSpec;

public class Transfer implements Subsystem {
    private final double POWER_IN = -1;
    private final double POWER_OUT = -1;
    private final double OPEN = 1;
    private final double CLOSE = -1;

    public enum STATES {IDLE, IN, OUT}

    STATES state = STATES.IDLE;

    private final DcMotorEx motor;
    private final Servo servo;

    public Transfer(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "transfer");
        servo = hardwareMap.get(Servo.class, "transfer_servo");
    }

    @Override
    public void init() {
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
                closeServo();
                break;
            case IN:
                motor.setPower(POWER_IN);
                openServo();
                break;
            case OUT:
                motor.setPower(POWER_OUT);
                openServo();
        }

    }

    @Override
    public void stop() {
        setState(STATES.IDLE);
        motor.setPower(0);
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
