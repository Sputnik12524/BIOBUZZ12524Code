package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Scheduler {
    private final HashSet<Command> conflicts = new HashSet<>(); // ради оптимизации
    private Set<Command> scheduledCommands = new HashSet<>();
    private Map<Subsystem, Command> requirementsMap = new HashMap<>();

    public void cancel(Command command) {
        if (command == null) return;
        if (!scheduledCommands.remove(command)) return;
        removeRequirements(command);
        command.end(true);

    }

    public void cancelAll() {
        Set<Command> commands = new HashSet<>(scheduledCommands);
        for (Command command : commands) {
            cancel(command);
        }
        requirementsMap.clear();
    }

    public boolean schedule(Command command) {
        if (isScheduled(command)) return false;
        if (!haveConflicts(command)) return false;

        for (Command deleteCommand : conflicts) {
            cancel(deleteCommand);
        }
        conflicts.clear();
        scheduledCommands.add(command);
        for (Subsystem requirement : command.getRequirements()) {
            requirementsMap.put(requirement, command);
        }
        command.initialize();
        return true;
    }

    public void run() {
        Iterator<Command> iterator = scheduledCommands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();     //не делаем внутри execute действия с scheduler
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
        return command != null && scheduledCommands.contains(command);
    }

    private boolean haveConflicts(Command command) {
        conflicts.clear();
        Set<Subsystem> requirements = command.getRequirements();
        for (Subsystem sub : requirements) {
            Command conflict = requirementsMap.get(sub);
            if (conflict != null) {
                if (conflict.getPriority() > command.getPriority()) return false;
                else conflicts.add(conflict);
            }
        }
        return true;
    }
}
