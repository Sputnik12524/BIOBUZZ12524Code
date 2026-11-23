package org.firstinspires.ftc.teamcode.event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Object lock = new Object();
    private final Deque<RobotEvent> waiting = new ArrayDeque<>();
    private final List<Registration<?>> clientList = new CopyOnWriteArrayList<>();

    ExceptionPolicy policy;

    public EventBus(ExceptionPolicy policy) {
        this.policy = Objects.requireNonNull(policy, "bus");
    }


    public void publish(RobotEvent event) {
        synchronized (lock) {
            waiting.addLast(event);
        }
    }


    public <E extends RobotEvent> Subscription subscribe(Class<E> event, EventHandler<E> handler) {
        Objects.requireNonNull(event, "event");
        Objects.requireNonNull(handler, "handler");
        Registration<E> registration = new Registration<>(event, this, handler);
        clientList.add(registration);
        return registration;
    }


    public void dispatchPending() {
        List<RobotEvent> list;
        synchronized (lock) {
            list = new ArrayList<>(waiting);
            waiting.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                dispatch(list.get(i));
            } catch (RuntimeException e) {
                synchronized (lock) {
                    for (int j = list.size() - 1; j > i; j--) {
                        waiting.addFirst(list.get(j));
                    }
                }
                throw e;
            }
        }


    }

    void dispatch(RobotEvent event) {
        for (Registration<?> registration : clientList) {
            if (!registration.matches(event)) continue;
            try {
                registration.invoke(event);
            } catch (RuntimeException e) {
                //логируем
                if (policy == ExceptionPolicy.PROPAGATE) throw e ;
            }

        }
    }

    public void remove(Registration<?> registration) {
        clientList.remove(registration);
    }


    public void clear() {
        synchronized (lock) {
            waiting.clear();
        }
        for (Registration<?> registration : clientList) {
            registration.unsubscribe();
        }
        clientList.clear();
    }

    private static final class Registration<E extends RobotEvent> implements Subscription {
        Class<E> event;
        EventBus owner;
        EventHandler<? super E> handler;

        private Registration(Class<E> event, EventBus owner, EventHandler<? super E> handler) {
            this.event = event;
            this.owner = owner;
            this.handler = handler;
        }


        boolean matches(RobotEvent event) {
            return this.event.isInstance(event);
        }

        void invoke(RobotEvent event) {
            handler.onEvent(this.event.cast(event));
        }

        public void unsubscribe() {
            owner.remove(this);
        }

    }
}
