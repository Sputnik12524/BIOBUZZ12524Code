package org.firstinspires.ftc.teamcode.event;

public interface EventHandler<E extends RobotEvent> {
    void onEvent(E event);
}
