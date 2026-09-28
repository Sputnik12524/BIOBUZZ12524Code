package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.Intake;

public class IntakeTest extends LinearOpMode {
    public Intake in;
Telemetry telemetry;
    @Override
    public void runOpMode() throws InterruptedException {
        in = new Intake(this);
        int state =0;
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                state = 1;
                in.rotateIn();
            }
            if (gamepad1.b) {
                state = -1;
                in.rotateOut();
            }
            if (gamepad1.x) {
                in.rotateStop();
                state = 0;
            }
            telemetry.update();
            telemetry.addData("Out", state); 
        }

    }
}
