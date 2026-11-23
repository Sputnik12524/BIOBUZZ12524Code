package org.firstinspires.ftc.teamcode.event.events;

import org.firstinspires.ftc.teamcode.event.RobotEvent;

public class PollenInTransferEvent implements RobotEvent {
    private final int count;

    public PollenInTransferEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
