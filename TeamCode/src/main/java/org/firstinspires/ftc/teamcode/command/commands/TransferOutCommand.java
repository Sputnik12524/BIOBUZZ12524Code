package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.Set;

public class TransferOutCommand implements Command {
    private Set<Subsystem> requirements;
    Transfer tr;

    public TransferOutCommand(Transfer tr) {
        this.tr = tr;
        requirements = Set.of(tr);
    }

    public void initialize() {
        tr.setState(Transfer.STATES.IDLE);
    }

    public void execute() {
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        tr.stop();
    }

    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    public int getPriority() {
        return 50;
    }
}
