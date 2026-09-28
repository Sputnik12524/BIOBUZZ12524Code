package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Set;

public interface Command {
    void initialize();
    void execute();
    boolean isFinished();
    void end(boolean interrupted);
    Set<Subsystem> getRequirements();
    default int getPriority(){
        return 0;
    }
}
