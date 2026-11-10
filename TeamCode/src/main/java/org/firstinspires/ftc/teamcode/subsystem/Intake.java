package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;




public class Intake implements Subsystem {
    private final double POWER_IN = 1;
    private final double POWER_OUT = -1;

    public enum STATES {IDLE, IN, OUT}

    STATES state = STATES.IDLE;

    private final DcMotorEx intake;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
    }

    public void init() {
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(STATES.IDLE);
    }

    public void stop() {
        setState(STATES.IDLE);
        intake.setPower(0);
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void update() {
        switch (state) {
            case IDLE:
                intake.setPower(0);
                break;
            case IN:
                intake.setPower(POWER_IN);
                break;
            case OUT:
                intake.setPower(POWER_OUT);
                break;

        }
    }

}
