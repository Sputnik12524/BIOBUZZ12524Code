package org.firstinspires.ftc.teamcode.input.bindings;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.command.Scheduler;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.input.ButtonHandler;
import org.firstinspires.ftc.teamcode.input.CommandBindings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Binding {
    Scheduler scheduler;

    public Binding(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    List<CommandBindings> bindings = new ArrayList<>();

    public void bind(
            ButtonHandler button,
            BindingMode mode,
            Supplier<Command> command
    ) {
        bindings.add(new CommandBindings(
                button,
                mode,
                command
        ));
    }

    public void update() {
        for (CommandBindings binding : bindings) {
            binding.update(scheduler);
        }
    }
}
