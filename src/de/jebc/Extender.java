package de.jebc;

public class Extender<T> {

    private OutPin<T> out = new OutPinImpl<T>();
    private InPin<T> in = new InPin<T>() {

        @Override
        public void receive(T message) throws Exception {
            out.send(message);
        }
    };

    public InPin<T> In() {
        return in;
    }

    public OutPin<T> Out() {
        return out;
    }
}
