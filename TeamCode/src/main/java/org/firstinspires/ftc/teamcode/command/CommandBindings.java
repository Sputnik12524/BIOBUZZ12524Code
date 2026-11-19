package org.firstinspires.ftc.teamcode.command;

import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.ButtonHandler;


import java.util.function.Supplier;

public class CommandBindings {
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
                    if (scheduler.isScheduled(activeCommand)) activeCommand = null;
                    else scheduler.addCommand(activeCommand);

                }
                if (activeCommand != null && button.wasReleased()) {
                    scheduler.cancel(activeCommand);
                    activeCommand = null;
                }
                break;
            case PRESS:
                if (button.wasPressed()) {
                    scheduler.addCommand(commandFactory.get());
                }
                break;
            case TOGGLE:
                if (button.wasPressed()) {
                    if (activeCommand == null || !scheduler.isScheduled(activeCommand)) {
                        activeCommand = commandFactory.get();
                        if (scheduler.isScheduled(activeCommand)) activeCommand = null;
                        else scheduler.addCommand(activeCommand);
                    } else {
                        scheduler.cancel(activeCommand);
                        activeCommand = null;
                    }
                }
                break;
        }
    }
}
