package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.Set;

public class TransferCommand implements Command {
    private final Set<Subsystem> requirements;
    private final Transfer.STATES state;
    Transfer tr;

    public TransferCommand(Transfer tr, Transfer.STATES state) {
        this.tr = tr;
        this.state = state;
        requirements = Set.of(tr);
    }

    public void initialize() {
        tr.setState(state);
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
