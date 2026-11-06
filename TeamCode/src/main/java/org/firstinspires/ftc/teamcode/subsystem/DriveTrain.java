package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DriveTrain implements Subsystem {

    public static double POSITION_FOR_SHOOT = 0.08; //При тесте подошло 0.08, но был 1 блокиратор. Нужен тест для второго
    public static double POSITION_FOR_REMOVAL = 0;
    public static double multiplier = 1;

    public enum BlockerState {IDLE, BLOCKER_FOR_SHOT}

    public BlockerState state = BlockerState.IDLE;

    private final DcMotorEx leftFront;
    private final DcMotorEx leftBack;
    private final DcMotorEx rightFront;
    private final DcMotorEx rightBack;
    private final Servo blockerFront;
    public final Servo blockerBack;


    public DriveTrain(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        blockerFront = hardwareMap.get(Servo.class, "blockerServoFront");
        blockerBack = hardwareMap.get(Servo.class, "blockerServoBack");
    }

    public void init() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setState(BlockerState.IDLE);
    }

    public void stop() {
        setState(BlockerState.IDLE);
    }

    public void update() {
        switch (state) {
            case IDLE:
                setStopMotors();
                blockerFront.setPosition(POSITION_FOR_REMOVAL);
                blockerBack.setPosition(POSITION_FOR_REMOVAL);
                break;

            case BLOCKER_FOR_SHOT:
                setStopMotors();
                blockerFront.setPosition(POSITION_FOR_SHOOT);
                blockerBack.setPosition(POSITION_FOR_SHOOT);
                break;
        }
    }

    public void setPower(double main, double side, double rotation) {
        leftFront.setPower(multiplier * (main + side + rotation));
        leftBack.setPower(multiplier * (main - side + rotation));
        rightFront.setPower(multiplier * (main - side - rotation));
        rightBack.setPower(multiplier * (main + side - rotation));
    }

    public void setState(BlockerState state) {
        this.state = state;
    }

    public void setStopMotors() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

}
