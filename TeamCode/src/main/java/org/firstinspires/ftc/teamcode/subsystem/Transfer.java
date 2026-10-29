package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer implements Subsystem {
    private final double POWER_IN = 1;
    private final double POWER_OUT = -1;

    public enum STATES {IDLE, IN, OUT}

    STATES state = STATES.IDLE;

    private final DcMotorEx transfer;

    public Transfer(HardwareMap hardwareMap) {
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
    }

    public void init() {
        transfer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transfer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(STATES.IDLE);
    }

    public void stop() {
        setState(STATES.IDLE);
        transfer.setPower(0);
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void update() {
        switch (state) {
            case IDLE:
                transfer.setPower(0);
                break;
            case IN:
                transfer.setPower(POWER_IN);
                break;
            case OUT:
                transfer.setPower(POWER_OUT);
                break;

        }
    }

}
