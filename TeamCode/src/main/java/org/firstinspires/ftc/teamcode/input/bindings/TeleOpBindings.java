package org.firstinspires.ftc.teamcode.input.bindings;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.command.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.input.Binding;
import org.firstinspires.ftc.teamcode.input.BindingMode;
import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class TeleOpBindings extends Binding {
    Robot robot;
    public TeleOpBindings(Robot robot){
        super(robot.scheduler);
        this.robot = robot;
    }
    public void configure(){
        bind(robot.gamepad1.A, BindingMode.TOGGLE,
                () -> new IntakeCommand(robot.in, Intake.STATES.IN));

    }
}
