package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.input.GamepadManager;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    List<Subsystem> subsystem= new ArrayList<>();
    public final Scheduler scheduler;
    public final Shooter sh;
    public final GamepadManager gamepad1;
    public final GamepadManager gamepad2;

    public Robot(HardwareMap hardwareMap, Gamepad rawGamepad1, Gamepad rawGamepad2) {
        gamepad1 = new GamepadManager(rawGamepad1);
        gamepad2 = new GamepadManager(rawGamepad2);
        scheduler = new Scheduler();
        sh = new Shooter(hardwareMap);
        subsystem.add(sh);
    }

    public void update() {
        gamepad1.update();
        gamepad2.update();
        scheduler.run();
        for(Subsystem sub: subsystem){
            sub.update();
        }
    }

    public void init() {
        for(Subsystem sub: subsystem){
            sub.init();
        }
    }

    public void stop() {
        for(Subsystem sub: subsystem){
            sub.stop();
        }
    }
}
