package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Scheduler {
    private final HashSet<Command> conflicts = new HashSet<>(); // ради оптимизации
    private final Set<Command> scheduledCommands = new LinkedHashSet<>();
    private final Map<Subsystem, Command> requirementsMap = new HashMap<>();
    private final Queue<Command> schedulePending = new ArrayDeque<>();
    private boolean wasInterrupted = false;

     void cancel(Command command) {
        if (command == null) return;
        schedulePending.removeIf(command::equals);
        if (!scheduledCommands.remove(command)) return;
        removeRequirements(command);
        wasInterrupted = true;
        command.end(true);
        wasInterrupted = false;

    }

    public void cancelAll() {
        Set<Command> commands = new HashSet<>(scheduledCommands);
        for (Command command : commands) {
            cancel(command);
        }
        requirementsMap.clear();
        schedulePending.clear();
    }

    public void addCommand(Command command) {
        if(wasInterrupted) return;
        schedulePending.add(command);
    }


    private void schedule() {
        Queue<Command> queue = new ArrayDeque<>(schedulePending);
        schedulePending.clear();
        while (!queue.isEmpty()) {
            Command command = queue.poll();
            if (isScheduled(command) || !haveConflicts(command)) continue;

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
    }


    public void run() {
        Iterator<Command> iterator = scheduledCommands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();
            if (command.isFinished()) {
                removeRequirements(command);
                iterator.remove();
                command.end(false);
            }

        }
        schedule();
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
                if (conflict.canInterrupt() ||
                        conflict.getPriority() <= command.getPriority()) conflicts.add(conflict);
                else return false;
            }
        }
        return true;
    }
}