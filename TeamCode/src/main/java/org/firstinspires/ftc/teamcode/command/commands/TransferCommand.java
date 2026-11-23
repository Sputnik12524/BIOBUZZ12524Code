package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;
import java.util.Set;

public class TransferCommand implements Command {
    Transfer tr;
    Transfer.STATES state;
    Set<Subsystem> requirements;

    public TransferCommand(Transfer tr, Transfer.STATES state) {
        this.tr = tr;
        this.state = state;
        requirements = Set.of(tr);
    }

    @Override
    public void initialize() {
        tr.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
        tr.stop();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }
}
