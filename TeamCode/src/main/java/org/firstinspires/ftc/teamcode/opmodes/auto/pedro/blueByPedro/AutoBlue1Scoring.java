package org.firstinspires.ftc.teamcode.opmodes.auto.pedro.blueByPedro;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="Blue Scoring", group="blue")
public class AutoBlue1Scoring extends LinearOpMode {
    Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    Pose startPoseE1 = p.of(79,9,90);
    Pose scoringPose = p.of(84, 23, 90);
    Pose parkPose = p.of(131, 22, 90);

    @Override
    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPoseE1);
        Scheduler.reset();

        waitForStart();
        schedule(autoRoutine());
        while(opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, scoringPath()),
                follow(follower, park())
        );
    }
    private Path scoringPath() {
        return line(startPoseE1, scoringPose).constant(scoringPose);
    }
    private Path park(){
        return line(scoringPose, parkPose).constant(parkPose);
    }
}
