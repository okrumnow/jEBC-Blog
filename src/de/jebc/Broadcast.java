package de.jebc;

public class Broadcast<T> {

    private InPin<T> inPin = new InPin<T>() {

        @Override
        public void receive(T message) throws Exception {
            Out().send(message);
        }
    };
    private OutPin<T> outPin = new BroadcastPin<T>();

    public InPin<T> In() {
        return inPin;
    }

    public OutPin<T> Out() {
        return outPin;
    }
}
