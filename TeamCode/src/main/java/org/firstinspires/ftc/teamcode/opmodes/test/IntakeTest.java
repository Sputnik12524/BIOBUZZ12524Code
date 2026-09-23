package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.modules.Intake;

public class IntakeTest extends LinearOpMode {
    public Intake in;

    @Override
    public void runOpMode() throws InterruptedException {
        in = new Intake(this);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                in.rotateIn();
            }
            if (gamepad1.b) {
                in.rotateOut();
            }
            if (gamepad1.x) {
                in.rotateStop();
            }
        }
    }
}
