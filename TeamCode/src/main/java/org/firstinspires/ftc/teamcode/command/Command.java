package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Set;

public interface Command {
    void initialize();

    default void execute() {
    }

    default boolean isFinished() {
        return false;
    }

    void end(boolean interrupted);

    Set<Subsystem> getRequirements();

    default int getPriority() {
        return 0;
    }
}
