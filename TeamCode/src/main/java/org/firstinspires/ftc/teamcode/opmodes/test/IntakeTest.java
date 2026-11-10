package org.firstinspires.ftc.teamcode.opmodes.test;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.IntakeBindings;


@TeleOp(name = "Inake Test", group = "test")
public class IntakeTest extends LinearOpMode {
    Robot robot;
    IntakeBindings bindings;

    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        bindings = new IntakeBindings(robot, robot.gamepad1);
        robot.init();
        bindings.configure();

        waitForStart();
        while (opModeIsActive()) {
            robot.update();
            bindings.update();

        }
    }

}

