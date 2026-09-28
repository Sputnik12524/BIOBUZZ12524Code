package org.firstinspires.ftc.teamcode.command.commands;

import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TransferINCommand implements Command {
    Transfer transfer;
    public TransferINCommand(Transfer transfer){
        this.transfer = transfer;
    }
    public void initialize(){
transfer.setState(Transfer.STATES.IN);
    }
    public void execute(){
    }
    public boolean isFinished(){
        return false;
    }
    public void end(boolean interrupted){
        transfer.stop();
    }


    public Set<Subsystem> getRequirements(){
        return Set.of(transfer);
    }
    public int getPriority(){
        return 50;
    }
}
