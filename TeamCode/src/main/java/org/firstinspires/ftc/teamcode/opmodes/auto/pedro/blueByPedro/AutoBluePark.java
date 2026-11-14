package org.firstinspires.ftc.teamcode.opmodes.auto.pedro.blueByPedro;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="Blue Park", group="blue")
public class AutoBluePark extends LinearOpMode {
    Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    Pose startPoseE1 = p.of(117,15,90);
    Pose startPoseE4 = p.of(132,80,-90);
    Pose startPoseE3 = p.of(132, 56, -90);
    Pose parkPoseE1 = p.of(132, 20, 90);
    Pose parkPoseE43 = p.of(132, 48, -90);

    @Override
    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPoseE1);
        Scheduler.reset();

        waitForStart();
        schedule(follow(follower, park()));
        while(opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }
    }
    private Path park() {
        return line(startPoseE1, parkPoseE1).constant(parkPoseE1);
    }
}
