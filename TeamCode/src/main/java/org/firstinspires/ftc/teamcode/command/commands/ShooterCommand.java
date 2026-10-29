package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Set;

public class ShooterCommand implements Command {
    private final Set<Subsystem> requirements;
    private final Shooter.ShooterStates state;
    Shooter sh;

    public ShooterCommand(Shooter sh, Shooter.ShooterStates state) {
        this.sh = sh;
        this.state = state;
        requirements = Set.of(sh);
    }
    public ShooterCommand(Shooter sh, Shooter.ShooterStates state, double targetVelocity) {
        this.sh = sh;
        sh.setTargetVelo(targetVelocity);
        this.state = state;
        requirements = Set.of(sh);
    }

    public void initialize() {
        sh.setState(state);
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
