package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Shooter;

@TeleOp(name = "ServoTest", group = "test")
@Config
public class ServoTest extends LinearOpMode {

    Shooter sh;
    public static double POS_MIN = 0;
    public static double POS_MAX = 1;

    double pose = 0;

    public void runOpMode() {
        sh = new Shooter(hardwareMap);

        sh.setAdjusterPose(POS_MIN);
        waitForStart();
        while (opModeIsActive()) {
            sh.setAdjusterPose(pose);
            if (gamepad1.dpad_up && pose <= POS_MAX) {
                pose += 0.005;
            } else if (gamepad1.dpad_down && pose >= POS_MIN) {
                pose -= 0.005;
            }
            telemetry.addData("pose:", pose);
            telemetry.addData("pose from adj", sh.getAdjusterPose());
            telemetry.update();

        }
    }
}
