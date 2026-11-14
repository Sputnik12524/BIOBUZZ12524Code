package org.firstinspires.ftc.teamcode.opmodes.auto.pedro.redByPedro;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.api.PoseFactory;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous(name = "Red Park", group = "1")
public class AutoRedPark extends LinearOpMode {
    Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    Pose startPoseA6 = poseFactory.of(9, 132, -90);
    Pose startPoseA4 = poseFactory.of(9, 78, 90);
    Pose startPoseA3 = poseFactory.of(9, 60, 90);
    Pose parkPoseA5 = poseFactory.of(9, 120, -90);
    Pose parkPoseA45 = poseFactory.of(9, 95, 90);


    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPoseA6);
        Scheduler.reset();

        waitForStart();
        schedule(follow(follower, goToPark()));

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }
    }

    private Path goToPark() {
        return line(startPoseA6, parkPoseA5).constant(startPoseA6);
    }
}
