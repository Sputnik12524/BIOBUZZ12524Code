package org.firstinspires.ftc.teamcode.opmodes.auto.pedro.redByPedro;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.math.Vector2D;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.curves.bezier.BezierCurve;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Red Scoring", group = "2")
public class AutoRed1Scoring extends LinearOpMode {
    Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    final Pose startPose = poseFactory.of(55, 9, 90);
    final Pose scoringPose = poseFactory.of(55, 26, 90);
    final Pose controlPose = poseFactory.of(16, 32, 90);
    final Pose parkPose = poseFactory.of(9, 97, 90);


    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        BezierCurve curve = new BezierCurve(startPose, controlPose, parkPose);
        Vector2D point = curve.get(0.5);
        Scheduler.reset();

        waitForStart();
        schedule(autoRoutine());
        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, scoringPath()),
                follow(follower, curvePath())
        );
    }

    public Path scoringPath() {
        return line(startPose, scoringPose).constant(scoringPose);
    }

    public Path curvePath() {
        return Paths.curve(startPose, controlPose, parkPose);
    }
}
