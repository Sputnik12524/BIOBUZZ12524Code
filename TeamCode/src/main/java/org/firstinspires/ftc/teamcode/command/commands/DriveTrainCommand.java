package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Set;

public class DriveTrainCommand implements Command {
    private final Set<Subsystem> requirements;
    private final DriveTrain.BlockerState state;
    DriveTrain dt;

    public DriveTrainCommand (DriveTrain dt, DriveTrain.BlockerState state){
        this.dt = dt;
        this.state = state;
        requirements = Set.of(dt);
    }



    public void initialize() {
        dt.setState(state);
    }

    public void end(boolean interrupted) {
        dt.stop();
    }

    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    public int getPriority() {
        return 1;
    }
}
