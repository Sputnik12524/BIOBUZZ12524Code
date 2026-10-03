package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

public class Robot {
    public final Scheduler scheduler;
    public final Transfer tr;

    public Robot(LinearOpMode opMode) {
        scheduler = new Scheduler();
        tr = new Transfer(opMode);

    }

    public void update() {
        scheduler.run();
        tr.update();
    }

    public void init() {
        tr.init();
    }

    public void stop() {
        tr.stop();
    }
}
