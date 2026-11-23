package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.command.commands.TransferCommand;
import org.firstinspires.ftc.teamcode.event.EventBus;
import org.firstinspires.ftc.teamcode.event.events.PollenInTransferEvent;
import org.firstinspires.ftc.teamcode.event.events.TransferOvercrowdedEvent;
import org.firstinspires.ftc.teamcode.subsystem.Transfer;

public class Coordinator {
    private final EventBus eventBus;
    private final Robot robot;

    public Coordinator(EventBus eventBus, Robot robot) {
        this.eventBus = eventBus;
        this.robot = robot;
        subscribe();
    }

    private void subscribe() {
    }


}
