package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.ShooterBindings;


@TeleOp(name = "Shooter Velo Test", group = "test")
@Config
public class ShooterTest extends LinearOpMode {
    Robot robot;
    ShooterBindings bindings;

    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        bindings = new ShooterBindings(robot, robot.gamepad1);
        robot.init();
        bindings.configure();

        waitForStart();
        while (opModeIsActive()) {
            robot.update();
            bindings.update();
        }
    }

}
