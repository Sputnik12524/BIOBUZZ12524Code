package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.Shooter;

@TeleOp(name="Shooter Velocity Test", group="test")
@Config
public class ShooterVelocityTest extends LinearOpMode {

    public static double VELOCITY = 10;

    boolean stateA = false;
    boolean stateB = false;

    boolean motorState = false;
    boolean mState = false;


    @Override
    public void runOpMode() {
        Shooter sh = new Shooter(this);

        Telemetry dashTele = FtcDashboard.getInstance().getTelemetry();

        MultipleTelemetry t = new MultipleTelemetry(telemetry, dashTele);

        waitForStart();

        while (opModeIsActive()){

            if(gamepad1.a && !stateA && !motorState) {
                sh.setTargetVelo(VELOCITY);
                motorState = true;
            } else if (gamepad1.a && !stateA && motorState) {
                sh.shootStop();
                motorState = false;
            }


            stateA = gamepad1.a;
            stateB = gamepad1.b;
            t.addData("target velo = ", VELOCITY);
            t.addData("current velo upper = ", sh.getVelocityUpper());
            t.addData("current velo lower = ", sh.getVelocityLower());

            t.update();
        }
    }
}
