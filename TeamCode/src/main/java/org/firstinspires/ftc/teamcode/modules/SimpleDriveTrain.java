package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.acmerobotics.dashboard.config.Config;

@Config

public class SimpleDriveTrain {
//    public final DcMotorEx leftFront;
//    public final DcMotorEx leftBack;
//    public final DcMotorEx rightFront;
//    public final DcMotorEx rightBack;
    public final Servo blocker;


    public static double multiplier = 1;
    public static double POSITION_FOR_SHOOT = 0;
    public static double POSITION_FOR_REMOVAL = 0;


    public SimpleDriveTrain(LinearOpMode opMode) {
//        this.leftFront = opMode.hardwareMap.get(DcMotorEx.class, "leftFront");
//        this.leftBack = opMode.hardwareMap.get(DcMotorEx.class, "leftBack");
//        this.rightFront = opMode.hardwareMap.get(DcMotorEx.class, "rightFront");
//        this.rightBack = opMode.hardwareMap.get(DcMotorEx.class, "rightBack");
        this.blocker = opMode.hardwareMap.get(Servo.class, "blockerServo");

//        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

//    public void setPower (double main, double side, double rotation) {
//        leftFront.setPower(multiplier * (main + side + rotation));
//        leftBack.setPower(multiplier * (main - side + rotation));
//        rightFront.setPower(multiplier * (main - side - rotation));
//        rightBack.setPower(multiplier * (main + side - rotation));
//    }

    public void setBlockerForShoot() {
        blocker.setPosition(POSITION_FOR_SHOOT);
    }

    public void setBlockerForRemoval() {
        blocker.setPosition(POSITION_FOR_REMOVAL);
    }


}
