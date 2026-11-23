package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.DriveByGamepadCommand;
import org.firstinspires.ftc.teamcode.input.bindings.TeleOpBindings;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group ="0")

public class TeleOp extends LinearOpMode {
    Robot robot;
    TeleOpBindings binding;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        binding = new TeleOpBindings(robot);
        robot.init();
        binding.configure();
        waitForStart();
        robot.scheduler.addCommand(new DriveByGamepadCommand(robot));

        while (opModeIsActive()) {
            robot.update();
            binding.update();
        }
    }
}
