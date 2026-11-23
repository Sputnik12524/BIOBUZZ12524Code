package org.firstinspires.ftc.teamcode.command.commands;


import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.Set;

public class OuttakePollenCommand implements Command {
    Set<Subsystem> requirements;
    Intake in;
    Transfer tr;

    public OuttakePollenCommand(Robot robot) {
        in = robot.in;
        tr = robot.tr;
        requirements = Set.of(in, tr);
    }

    @Override
    public void initialize() {
        in.setState(Intake.STATES.OUT);
        tr.setState(Transfer.STATES.OUT);
    }

    @Override
    public void end(boolean interrupted) {
        in.stop();
        tr.stop();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }
}
