package summer.laii.SyntacticAnalysis;

import java.util.ArrayList;

public class RightSide {
    ArrayList<String> rightSide;
    ArrayList<String> terminals;

    public RightSide() {
        rightSide = new ArrayList<>();
        terminals = new ArrayList<>();
    }

    public void addElement(String element) { rightSide.add(element); }

    public void addTerminal(String terminal) { terminals.add(terminal); }

    public ArrayList<String> getRightSide() { return rightSide; }

    public ArrayList<String> getTerminals() { return terminals; }
}
