package de.jebc;


public abstract class Watcher<T> {

    private OutPin<T> out = new OutPinImpl<T>();
    private InPin<T> in = new InPin<T>() {

        @Override
        public void receive(T message) throws Exception {
            inspect(message);
            out.send(message);
        }
    };

    public Watcher() {
        super();
    }

    public InPin<T> In() {
        return in;
    }

    public OutPin<T> Out() {
        return out;
    }

    protected abstract void inspect(T message);

}