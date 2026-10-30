package org.firstinspires.ftc.teamcode.modules;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config

@TeleOp(name = "DriveTrain with blocker test", group = "1")
public class DriveTrainTest extends LinearOpMode {
    SimpleDriveTrain dt;
    public static double POSITION = 0;
    public static double ADD = 0.01;
    public static double ADD_MORE = 0.1;

    @Override
    public void runOpMode() {
        dt = new SimpleDriveTrain(this);
        waitForStart();
        while (opModeIsActive()) {
            //   double main = -gamepad1.left_stick_y;
            // double side = gamepad1.left_stick_x;
            // double rotate = gamepad1.right_trigger - gamepad1.left_trigger;

            // dt.setPower(main, side, rotate);

            dt.blocker.setPosition(POSITION);
            if (gamepad1.aWasPressed()) POSITION += ADD;
            if (gamepad1.bWasPressed()) POSITION -= ADD;
            if (gamepad1.xWasPressed()) POSITION += ADD_MORE;
            if (gamepad1.yWasPressed()) POSITION -= ADD_MORE;


            //  telemetry.addData("main", main);
            //  telemetry.addData("side", side);
            //  telemetry.addData("rotate", rotate);
            telemetry.addData("Servo position", POSITION);
            telemetry.update();
        }

    }

}
