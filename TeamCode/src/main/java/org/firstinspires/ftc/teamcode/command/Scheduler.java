package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Scheduler {
    Set<Command> scheduledCommands = new HashSet<>();
    Map<Subsystem, Command> requirementsMap = new HashMap<>();


    void cancel(Command command) {
        command.end(true);
        scheduledCommands.remove(command);
        removeRequirements(command);

    }

    public void cancelAll() {
        for (Command command : scheduledCommands) {
            command.end(true);
        }
        scheduledCommands.clear();
        requirementsMap.clear();
    }

    public void schedule(Command command) {
        if (isScheduled(command)) return;
        Set<Command> conflict = getConflicts(command);
        if (conflict == null) return;

        for (Command deleteCommand : conflict) {
            cancel(deleteCommand);
        }
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
    }

    private void removeRequirements(Command command) {
        for (Subsystem requirement : command.getRequirements()) {
            if (requirementsMap.get(requirement) == command) {
                requirementsMap.remove(requirement);
            }
        }
    }

    public boolean isScheduled(Command command) {
        return scheduledCommands.contains(command);
    }

    private Set<Command> getConflicts(Command command) {
        Set<Command> conflicts = new HashSet<>();
        Set<Subsystem> requirements = command.getRequirements();
        for (Subsystem sub : requirements) {
            Command conflict = requirementsMap.get(sub);
            if (conflict != null) {
                if (conflict.getPriority() >= command.getPriority()) return null;
                else conflicts.add(conflict);
            }


        }
        return conflicts;
    }
}
