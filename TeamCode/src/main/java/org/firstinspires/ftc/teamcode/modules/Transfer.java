package org.firstinspires.ftc.teamcode.modules;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Transfer {
    private DcMotorEx trMotor;
    public Transfer(LinearOpMode opmode){
        trMotor = opmode.hardwareMap.get(DcMotorEx.class, "transfer");
    }
    public void turnIn(){
        trMotor.setPower(1);
    }
    public void turnOut(){
        trMotor.setPower(-1);
    }
    public void stop(){
        trMotor.setPower(0);
    }
}
