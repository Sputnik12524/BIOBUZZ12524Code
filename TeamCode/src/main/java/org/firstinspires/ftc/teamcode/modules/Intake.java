package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Intake {
    private final DcMotorEx intake;
    public Intake(LinearOpMode opMode) {
        intake = opMode.hardwareMap.get(DcMotorEx.class, "intake");
    }
    public void rotateIn(){
        intake.setPower(1);
    }
    public void rotateOut(){
        intake.setPower(-1);
    }
    public void rotateStop(){
        intake.setPower(0);
    }
}
