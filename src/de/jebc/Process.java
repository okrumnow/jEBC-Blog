package de.jebc;

public abstract class Process<TInput, TOutput> {

    private OutPin<TOutput> outpin = new OutPinImpl<TOutput>();
    private InPin<TInput> inpin = new InPin<TInput>() {

        @Override
        public void receive(TInput message) throws Exception {
            process(message);
        }

    };

    protected abstract void process(TInput message) throws Exception;

    public InPin<TInput> Start() {
        return inpin;
    }

    public OutPin<TOutput> Result() {
        return outpin;
    }

}
