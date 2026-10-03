package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Scheduler {
    private final HashSet<Command> conflicts = new HashSet<>();
    private Set<Command> scheduledCommands = new HashSet<>();
    private Map<Subsystem, Command> requirementsMap = new HashMap<>();
    private Map<Subsystem, Command> defaultCommands = new HashMap<>();


    public void setDefaultCommand(Command command) {
        for (Subsystem subsystem : command.getRequirements()) {
            defaultCommands.put(subsystem, command);
        }
    }

    public void runDefaultCommands() {
        for (Command command : defaultCommands.values()) {
            if (defaultCommands.get(command.getRequirements()) != null
                    && !isScheduled(command)) schedule(command);
        }
    }

    public void cancel(Command command) {
        if (command == null) return;
        if (!scheduledCommands.remove(command)) return;
        command.end(true);
        removeRequirements(command);

    }

    public void cancelAll() {
        Set<Command> commands = new HashSet<>(scheduledCommands);
        for (Command command : commands) {
            cancel(command);
        }
        requirementsMap.clear();
    }

    public void schedule(Command command) {
        if (isScheduled(command)) return;
        if (!getConflicts(command)) return;

        for (Command deleteCommand : conflicts) {
            cancel(deleteCommand);
        }
        conflicts.clear();
        scheduledCommands.add(command);
        for (Subsystem requirement : command.getRequirements()) {
            requirementsMap.put(requirement, command);
        }
        command.initialize();
    }

    public void run() {
        Iterator<Command> iterator = scheduledCommands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();
            if (command.isFinished()) {
                command.end(false);
                removeRequirements(command);
                iterator.remove();
            }

        }
        runDefaultCommands();
    }

    private void removeRequirements(Command command) {
        for (Subsystem requirement : command.getRequirements()) {
            if (requirementsMap.get(requirement) == command) {
                requirementsMap.remove(requirement);
            }
        }
    }

    public boolean isScheduled(Command command) {
        return command != null && scheduledCommands.contains(command);
    }

    private boolean getConflicts(Command command) {
        conflicts.clear();
        Set<Subsystem> requirements = command.getRequirements();
        for (Subsystem sub : requirements) {
            Command conflict = requirementsMap.get(sub);
            if (conflict != null) {
                if (conflict.getPriority() >= command.getPriority()) return false;
                else conflicts.add(conflict);
            }
        }
        return true;
    }
}
