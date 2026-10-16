package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.TransferBindings;


@TeleOp(name = "Transfer Test", group = "test")
public class TransferTest extends LinearOpMode {
    Robot robot;
    TransferBindings bindings;

    public void runOpMode() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
        bindings = new TransferBindings(robot, robot.gamepad1);
        robot.init();
        bindings.configure();

        waitForStart();
        while (opModeIsActive()) {
            robot.update();
            bindings.update();

        }
    }

}
