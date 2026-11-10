package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.Set;

public class IntakeCommand implements Command {
    private final Set<Subsystem> requirements;
    private final Intake.STATES state;
    Intake in;

    public IntakeCommand(Intake in,  Intake.STATES state) {
        this.in = in;
        this.state = state;
        requirements = Set.of(in);

    }

    public void initialize() {
        in.setState(state);
    }

    public void end(boolean interrupted) {
        in.stop();
    }

    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    public int getPriority() {
        return 50;
    }
}