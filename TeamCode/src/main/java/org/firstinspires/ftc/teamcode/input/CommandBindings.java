package org.firstinspires.ftc.teamcode.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.command.Scheduler;


import java.util.function.Supplier;

public class CommandBindings {
    BindingMode mode;
    ButtonHandler button;
    Supplier<Command> commandsFactory;
    private Command activeCommand;

    public CommandBindings(BindingMode mode,
                           ButtonHandler button,
                           Supplier<Command> commandsFactory) {
        this.mode = mode;
        this.button = button;
        this.commandsFactory = commandsFactory;

    }

    public void update(Scheduler scheduler) {
        switch (mode) {
            case HELD:
                if (button.wasPressed()) {
                    //activeCommand = commandsFactory.get();
                    scheduler.schedule(activeCommand);
                }
                if (activeCommand != null) {
                    if (button.wasReleased()) {
                        scheduler.cancel(activeCommand);
                    }
                    activeCommand = null;
                }

            case PRESS:
                if (button.wasPressed()) {

                }
            case TOGGLE:
                if (button.wasPressed()) {
                    if (activeCommand == null || !scheduler.isScheduled(activeCommand)) {
                        //activeCommand = commandsFactory.get();

                        scheduler.schedule(activeCommand);
                    } else {

                    }
                }
        }
    }
}
