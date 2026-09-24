package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.Transfer;

import java.lang.annotation.Target;
@TeleOp
public class TransferTest extends LinearOpMode {
Transfer tr;

@Override
    public void runOpMode(){
        tr = new Transfer(this);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a) tr.turnIn();
            if(gamepad1.b) tr.turnOut();
            if(gamepad1.x) tr.stop();
        }
    }
}
