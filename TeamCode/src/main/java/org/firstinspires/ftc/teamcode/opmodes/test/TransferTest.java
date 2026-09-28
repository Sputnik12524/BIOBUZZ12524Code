package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "Transfer Test", group = "test")
public class TransferTest extends LinearOpMode {
    Robot robot;
    public void runOpMode(){
        robot = new Robot(this);
    }

}
