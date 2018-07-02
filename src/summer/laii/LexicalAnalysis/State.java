package summer.laii.LexicalAnalysis;

import java.util.ArrayList;

public class State {
    private int stateNumber;
    private boolean isFinal;
    private ArrayList<String> transitionElements;

    public State(int stateNumber, ArrayList<String> transitionElements, boolean isFinal){
        this.stateNumber = stateNumber;
        this.transitionElements = transitionElements;
        this.isFinal = isFinal;
    }

    public State(int stateNumber, boolean isFinal) {
        this.stateNumber = stateNumber;
        this.isFinal = isFinal;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public ArrayList<String> getTransitionElements() {
        return transitionElements;
    }

    public void setTransitionElements(ArrayList<String> transitionElements) {
        this.transitionElements = transitionElements;
    }
}
