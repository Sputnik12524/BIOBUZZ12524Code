package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.input.bindings.TestBindings.TransferBindings;


@TeleOp(name = "Transfer Test", group = "test")
public class TransferTest extends LinearOpMode {
    Robot robot;
    TransferBindings bindings;

    @Override
    public void runOpMode() {
        try {
            robot = new Robot(hardwareMap, gamepad1, gamepad2);
            bindings = new TransferBindings(robot);
            robot.init();
            bindings.configure();

            waitForStart();
            while (opModeIsActive()) {
                robot.updateInput();
                bindings.update();
                robot.update();

            }
        } finally {
            robot.stop();
        }
    }
}
