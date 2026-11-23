package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.event.EventBus;
import org.firstinspires.ftc.teamcode.event.Subscription;
import org.firstinspires.ftc.teamcode.event.events.PollenInTransferEvent;
import org.firstinspires.ftc.teamcode.event.events.TransferOvercrowdedEvent;

public class Transfer implements Subsystem {
    private final double POWER_IN = -1;
    private final double POWER_OUT = -1;
    private final double OPEN = 1;
    private final double CLOSE = -1;

    public boolean pollenDetected = false;        //визуализация работы датчика
    private boolean wasPollenDetected = false; // чтобы избежать публикации события на каждом цикле
    public int count = 0;

    public enum STATES {IDLE, IN, OUT}

    STATES state = STATES.IDLE;

    private final DcMotorEx transfer;
    private final EventBus eventBus;
    private final Subscription pollenInTransferEvent;

    public Transfer(HardwareMap hardwareMap, EventBus eventBus) {
        this.eventBus = eventBus;
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
         pollenInTransferEvent = eventBus.subscribe(PollenInTransferEvent.class, event -> { // Конечно шина событий используется для взаимодействий между подсистемами, а не внутри одной, но у меня только трансфер
            if (event.getCount() > 3) eventBus.publish(new TransferOvercrowdedEvent());
        });
        pollenInTransferEvent.unsubscribe();

    }

    @Override
    public void init() {
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(STATES.IDLE);
        closeServo();
    }

    public void stop() {
        setState(STATES.IDLE);
        transfer.setPower(0);
    }

    private void pollenDetect() {
        if (pollenDetected && !wasPollenDetected) eventBus.publish(new PollenInTransferEvent(3));
        wasPollenDetected = pollenDetected;
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void update() {
        switch (state) {
            case IDLE:
                motor.setPower(0);
                motorExtra.setPower(0);
//                servoLeft.setPower(0);
//                servoRight.setPower(0);
                closeServo();
                break;
            case IN:
                motor.setPower(POWER_IN);
                motorExtra.setPower(0);
//                servoRight.setPower(0);
//                servoLeft.setPower(0);
                openServo();
                break;
            case OUT:
                motor.setPower(POWER_OUT);
                motorExtra.setPower(0);
//                servoRight.setPower(0);
//                servoLeft.setPower(0);
                openServo();
                break;
            case SHOOTING:
                motor.setPower(POWER_IN);
                motorExtra.setPower(POWER_IN);
//                servoRight.setPower(POWER_IN);
//                servoLeft.setPower(POWER_IN);
                openServo();
                break;
        }
        pollenDetect();
    }

    @Override
    public void stop() {
        setState(STATES.IDLE);
        motor.setPower(0);
        motorExtra.setPower(0);
//        servoLeft.setPower(0);
//        servoRight.setPower(0);
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public void closeServo() {
        servo.setPosition(CLOSE);
    }

    public void openServo() {
        servo.setPosition(OPEN);
    }
}
