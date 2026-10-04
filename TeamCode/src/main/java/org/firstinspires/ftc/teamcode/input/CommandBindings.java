package org.firstinspires.ftc.teamcode.input;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.command.Scheduler;


import java.util.function.Supplier;

public class    CommandBindings {
    BindingMode mode;
    ButtonHandler button;
    Supplier<Command> commandFactory;
    private Command activeCommand;

    public CommandBindings(ButtonHandler button,
                           BindingMode mode,
                           Supplier<Command> commandFactory) {
        this.mode = mode;
        this.button = button;
        this.commandFactory = commandFactory;

    }

    public void update(Scheduler scheduler) {
        switch (mode) {
            case HELD:
                if (button.wasPressed()) {
                    activeCommand = commandFactory.get();
                    if (!scheduler.schedule(activeCommand)) activeCommand = null;

                }
                if (activeCommand != null && button.wasReleased()) {
                    scheduler.cancel(activeCommand);
                    activeCommand = null;
                }
                break;
            case PRESS:
                if (button.wasPressed()) {
                    scheduler.schedule(commandFactory.get());
                }
                break;
            case TOGGLE:
                if (button.wasPressed()) {
                    if (activeCommand == null || !scheduler.isScheduled(activeCommand)) {
                        activeCommand = commandFactory.get();
                        if (!scheduler.schedule(activeCommand)) activeCommand = null;
                    } else {
                        scheduler.cancel(activeCommand);
                        activeCommand = null;
                    }
                }
                break;
        }
    }
}
