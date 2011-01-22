package de.jebc;

public abstract class Join<TInput1, TInput2, TOutput> {

    protected TInput1 data1;
    protected boolean data1present;
    protected TInput2 data2;
    protected boolean data2present;
    private InPin<TInput1> inPin1 = new InPin<TInput1>() {

        @Override
        public void receive(TInput1 message) {
            data1 = message;
            data1present = true;
            joinIfComplete();
        }
    };
    private InPin<TInput2> inPin2 = new InPin<TInput2>() {

        @Override
        public void receive(TInput2 message) {
            data2 = message;
            data2present = true;
            joinIfComplete();
        }
    };
    private OutPin<TOutput> outPin = new OutPinImpl<TOutput>();

    public InPin<TInput1> In1() {
        return inPin1;
    }

    public InPin<TInput2> In2() {
        return inPin2;
    }

    public OutPin<TOutput> Out() {
        return outPin;
    }

    protected void joinIfComplete() {
        if (data1present && data2present) {
            TOutput result = join(data1, data2);
            Out().send(result);
            data1present = data2present = false;
        }
    }

    protected abstract TOutput join(TInput1 input1, TInput2 input2);
}
