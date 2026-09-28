package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

public class Robot {
    public final Scheduler scheduler;
    public final Transfer transfer;

    public Robot(LinearOpMode opMode) {
        scheduler = new Scheduler();
        transfer = new Transfer(opMode);
    }

    public void init(){
        transfer.init();
    }

    public void stop(){
        transfer.stop();
    }
}
