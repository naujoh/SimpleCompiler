package summer.laii.SyntacticAnalysis;

import summer.laii.SymbolTable.Register;

import java.util.ArrayList;
import java.util.Stack;

public class SyntacticAnalyzer {
    private ArrayList<Register> sourceCode;
    private SyntacticAnalisisTable syntacticAnalisisTable;
    private Stack<String> stack;
    private String stackTransition, errors, elementToEvaluate;
    private ArrayList<String> elementsToEvaluate;
    private boolean isExpression;

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
        elementsToEvaluate = new ArrayList<>();
        stackTransition = "";
        errors = "";
        elementToEvaluate = "";
        isExpression = false;
    }

    public String printSyntacticTable() {
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
            boolean completedAnalysis = false, indexChanged = true, evaluatingElement=false;
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
                    if (evaluatingElement)
                        evaluatingElement = findElementToEvaluate(currentElement);
                    else if(currentElement.equals("def"))
                        evaluatingElement = findElementToEvaluate(currentElement);
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else if (stack.peek().equalsIgnoreCase("id") && currentElementCategory.equalsIgnoreCase("id")) {
                    if(evaluatingElement)
                        findElementToEvaluate(currentElement);
                    else
                        evaluatingElement = findElementToEvaluate(currentElement);
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                } else if (stack.peek().equalsIgnoreCase("di") && currentElementCategory.equalsIgnoreCase("di")) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                    findElementToEvaluate(currentElement);
                } else if (stack.peek().equalsIgnoreCase("ca") && currentElementCategory.equalsIgnoreCase("ca")) {
                    stack.pop();
                    currentElementIndex++;
                    indexChanged = true;
                    findElementToEvaluate(currentElement);
                } else {
                    indexChanged = false;
                    if (currentElementCategory.equalsIgnoreCase("id") ||
                        currentElementCategory.equalsIgnoreCase("di") ||
                        currentElementCategory.equalsIgnoreCase("ca")) {
                        switch (stack.peek()) {
                            case "OPERACION":
                                if (!evaluatingElement) evaluatingElement = true;
                                break;
                            case "EXPRESION":
                                if (!evaluatingElement) evaluatingElement = true;
                                isExpression = true;
                                break;
                        }
                        derivation = syntacticAnalisisTable.getDerivationOfRule(stack.peek(),
                                                                                currentElementCategory.toLowerCase());
                    } else {
                        switch (stack.peek()) {
                            case "TIPO":
                            case "OPERACION":
                            case "DECLARACION_FOR":
                                if (!evaluatingElement) evaluatingElement = true;
                                break;
                        }
                        derivation = syntacticAnalisisTable.getDerivationOfRule(stack.peek(),
                                                                                currentElement.toLowerCase());
                    }
                    if (derivation != null) {
                        if (derivation.get(0).equals("ε")) stack.pop();
                        else {
                            stack.pop();
                            for (int i = derivation.size() - 1; i > -1; i--) { stack.push(derivation.get(i)); }
                        }
                    } else {
                        if(currentElement.equals("$"))
                            errors = String.format("Error sintactico, falta instruccion de cierre del programa");
                        else
                            errors = String.format("Error sintactico, el elemento << %s >> mal formado en la linea %s",
                                    currentElement.toLowerCase(), sourceCode.get(currentElementIndex).getLineNumber());
                        completedAnalysis = true;
                    }
                }
            }
            System.out.println("e: "+elementsToEvaluate);
        }
    }

    private boolean findElementToEvaluate(String currentElement) {
        if(currentElement.equals("{") || currentElement.equals(";")) {
            if (currentElement.equals("{"))
                elementToEvaluate = elementToEvaluate.substring(0, elementToEvaluate.length() - 1);
            if(isExpression) isExpression = false;
            elementsToEvaluate.add(elementToEvaluate);
            elementToEvaluate = "";
            return false;
        }
        elementToEvaluate += " " +currentElement;
        return true;
    }

    public String getStackTransition() { return stackTransition; }

    public String getErrors() { return errors; }
}