package summer.laii.SyntacticAnalysis;

import summer.laii.SymbolTable.Register;

import java.util.ArrayList;
import java.util.Stack;

public class SyntacticAnalyzer {
    private ArrayList<Register> sourceCode;
    private SyntacticAnalisisTable syntacticAnalisisTable;
    private Stack<String> stack;
    private String stackTransition, errors;

    public SyntacticAnalyzer(ArrayList<Register> sourceCode) {
        this.sourceCode = sourceCode;
        if(sourceCode.size()>0) {
            Register r = new Register();
            r.setToken("$");
            r.setCategory("null");
            sourceCode.add(r);
        }
        syntacticAnalisisTable = new SyntacticAnalisisTable();
        stack = new Stack<>();
        stackTransition = "";
        errors = "";
    }

    public String printSintacticTable() {
        String rule = "", terminals = "", output = String.format("%-70s %-50s \n\n","REGLAS DE PRODUCCION", "TERMINALES");
        for(GramaticRule g : syntacticAnalisisTable.getTable()) {
            for(RightSide r : g.getRightSides()) {
                rule += g.getLeftSide() + " -> ";
                for(String s : r.getRightSide()) { rule += s + " "; }
                for(String s : r.getTerminals()) {
                    if(r.getTerminals().size()==1) terminals+= s;
                    else terminals += s + ", ";
                }
                output += String.format("%-70s %-50s \n", rule, terminals);
                rule = "";
                terminals = "";
            }
        }
        return output;
    }

    public void analyze() {
        if(sourceCode.size()>0) {
            boolean completedAnalysis = false, indexChanged = true;
            String currentElement = "", currentElementCategory = "";
            ArrayList<String> derivation;
            int currentElementIndex = 0;
            stack.push("$");
            stack.push("PROGRAMA");
            while (completedAnalysis == false) {
                stackTransition += stack + "\n";
                if (indexChanged == true) {
                    currentElement = sourceCode.get(currentElementIndex).getToken().trim();
                    currentElementCategory = sourceCode.get(currentElementIndex).getCategory();
                }
                if (stack.peek().equals("$") && currentElement.equals("$")) completedAnalysis = true;
                else if (stack.peek().equals(currentElement)) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else if (stack.peek().equalsIgnoreCase("id") && currentElementCategory.equalsIgnoreCase("id")) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else if (stack.peek().equalsIgnoreCase("di") && currentElementCategory.equalsIgnoreCase("di")) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else if (stack.peek().equalsIgnoreCase("ca") && currentElementCategory.equalsIgnoreCase("ca")) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else {
                    indexChanged = false;
                    if (currentElementCategory.equalsIgnoreCase("id") ||
                        currentElementCategory.equalsIgnoreCase("di") ||
                        currentElementCategory.equalsIgnoreCase("ca"))
                        derivation = syntacticAnalisisTable.getDerivationOfRule(stack.peek(), currentElementCategory.toLowerCase());
                    else
                        derivation = syntacticAnalisisTable.getDerivationOfRule(stack.peek(), currentElement.toLowerCase());
                    if (derivation != null) {
                        if (derivation.get(0).equals("ε")) stack.pop();
                        else {
                            stack.pop();
                            for (int i = derivation.size() - 1; i > -1; i--) {
                                stack.push(derivation.get(i));
                            }
                        }
                    } else {
                        errors = String.format("Error sintactico, el elemento << %s >> mal formado en la linea %s",
                                currentElement.toLowerCase(), sourceCode.get(currentElementIndex).getLineNumber());
                        completedAnalysis = true;
                    }
                }
            }
        }
    }

    public String getStackTransition() { return stackTransition; }

    public String getErrors() { return errors; }
}