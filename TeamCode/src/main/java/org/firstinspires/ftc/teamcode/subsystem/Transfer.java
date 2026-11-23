package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.event.EventBus;

public class Transfer implements Subsystem {
    private final double POWER_IN = -1;
    private final double POWER_OUT = -1;
    private final double OPEN = 1;
    private final double CLOSE = -1;

    public enum STATES {IDLE, IN, OUT}

    STATES state = STATES.IDLE;

    private final DcMotorEx motor;
    private final Servo servo;
    private final EventBus eventBus;

    public Transfer(HardwareMap hardwareMap, EventBus eventBus) {
        this.eventBus = eventBus;
        servo = hardwareMap.get(Servo.class, "transfer_servo");
        motor = hardwareMap.get(DcMotorEx.class, "transfer");
    }

    @Override
    public void init() {
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(STATES.IDLE);
        closeServo();
    }

    public void setState(STATES state) {
        this.state = state;
    }

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
                break;
        }
    }

    @Override
    public void stop() {
        setState(STATES.IN);
        motor.setPower(0);
    }


    public void closeServo() {
        servo.setPosition(CLOSE);
    }

    public void openServo() {
        servo.setPosition(OPEN);
    }
}
