package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.Set;

public class ShooterCommand implements Command {
    private final Set<Subsystem> requirements;
    private final Shooter.ShooterStates stateSh;
    Shooter sh;
    Transfer tr;

    public ShooterCommand(Shooter sh, Shooter.ShooterStates stateSh) {
        this.sh = sh;
        this.stateSh = stateSh;

        requirements = Set.of(sh);
    }

    public void initialize() {
        sh.setState(stateSh);
    }

    public void end(boolean interrupted) {
        sh.stop();
    }

    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    public int getPriority() {
        return 1;
    }
}
