package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

public class Robot {
    public final Scheduler scheduler;
    public final Transfer tr;
    public final GamepadManager gamepad1;
    public final GamepadManager gamepad2;

    public Robot(HardwareMap hardwareMap, Gamepad rawGamepad1, Gamepad rawGamepad2) {
        gamepad1 = new GamepadManager(rawGamepad1);
        gamepad2 = new GamepadManager(rawGamepad2);
        scheduler = new Scheduler();
        tr = new Transfer(hardwareMap);
    }

    public void update() {
        gamepad1.update();
        gamepad2.update();
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
