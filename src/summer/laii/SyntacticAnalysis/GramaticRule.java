package summer.laii.SyntacticAnalysis;

import java.util.ArrayList;

public class GramaticRule {
    private String leftSide;
    private ArrayList<RightSide> rightSides;

    public GramaticRule(String leftSide, ArrayList<RightSide> rightSides) {
        this.leftSide = leftSide;
        this.rightSides = rightSides;
    }

    public String getLeftSide() { return leftSide; }

    public void setLeftSide(String leftSide) { this.leftSide = leftSide; }

    public ArrayList<RightSide> getRightSides() { return rightSides; }

    public void setRightSides(ArrayList<RightSide> rightSides) { this.rightSides = rightSides; }
}
