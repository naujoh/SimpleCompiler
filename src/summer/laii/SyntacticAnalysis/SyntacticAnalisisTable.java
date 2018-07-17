package summer.laii.SyntacticAnalysis;

import java.util.ArrayList;

public class SyntacticAnalisisTable {
    private ArrayList<GramaticRule> table;
    private ArrayList<String> rulesWithEmptyElement;
    /*
    * Table structure
    *
    * [
    *   {leftSide, [{[rightSide], [terminals]}, {[rightSide], [terminals]}]}
    * ]
    *
    * */

    public SyntacticAnalisisTable() {
        rulesWithEmptyElement = new ArrayList<>();
        rulesWithEmptyElement.add("INSTRUCCIONES_OPTATIVAS");
        rulesWithEmptyElement.add("LISTA_ID'");
        rulesWithEmptyElement.add("OPERACION'");
        rulesWithEmptyElement.add("TERMINAL_INTERMEDIO'");
        rulesWithEmptyElement.add("SI2");
        rulesWithEmptyElement.add("EXPRESION''");

        table = new ArrayList<>();
        ArrayList<RightSide> rightSidesOfRule = new ArrayList<>();
        RightSide rightSide = new RightSide();
        rightSide.addElement("open>");
        rightSide.addElement("INSTRUCCIONES_OPTATIVAS");
        rightSide.addElement("<close");
        rightSide.addTerminal("open>");
        rightSidesOfRule.add(rightSide);
        GramaticRule gramaticRule = new GramaticRule("PROGRAMA", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("ε");
        rightSide.addTerminal("<close");
        rightSide.addTerminal("}");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("INSTRUCCION");
        rightSide.addElement(";");
        rightSide.addElement("INSTRUCCIONES_OPTATIVAS");
        rightSide.addTerminal("int");
        rightSide.addTerminal("flt");
        rightSide.addTerminal("bln");
        rightSide.addTerminal("str");
        rightSide.addTerminal("def");
        rightSide.addTerminal("id");
        rightSide.addTerminal("if");
        rightSide.addTerminal("for");
        rightSide.addTerminal("while");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("INSTRUCCIONES_OPTATIVAS", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("TIPO");
        rightSide.addElement("LISTA_ID");
        rightSide.addTerminal("int");
        rightSide.addTerminal("flt");
        rightSide.addTerminal("bln");
        rightSide.addTerminal("str");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("def");
        rightSide.addElement("TIPO");
        rightSide.addElement("LISTA_ID");
        rightSide.addTerminal("def");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("id");
        rightSide.addElement("=");
        rightSide.addElement("ELEMENTO_ASIGNADO");
        rightSide.addTerminal("id");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("SI1");
        rightSide.addElement("SI2");
        rightSide.addTerminal("if");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("for");
        rightSide.addElement("(");
        rightSide.addElement("PARA1");
        rightSide.addTerminal("for");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("while");
        rightSide.addElement("(");
        rightSide.addElement("MIENTRAS");
        rightSide.addTerminal("while");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("INSTRUCCION", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("int");
        rightSide.addTerminal("int");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("flt");
        rightSide.addTerminal("flt");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("bln");
        rightSide.addTerminal("bln");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("str");
        rightSide.addTerminal("str");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("TIPO", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("int");
        rightSide.addElement("DECLARACION_FOR'");
        rightSide.addTerminal("int");
        rightSide.addTerminal("flt");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("DECLARACION_FOR", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("id");
        rightSide.addElement("=");
        rightSide.addElement("ELEMENTO_ASIGNADO");
        rightSide.addTerminal("id");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("DECLARACION_FOR'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("id");
        rightSide.addElement("LISTA_ID'");
        rightSide.addTerminal("id");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("LISTA_ID", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement(",");
        rightSide.addElement("id");
        rightSide.addElement("LISTA_ID'");
        rightSide.addTerminal(",");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("LISTA_ID'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("OPERACION");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("ca");
        rightSide.addTerminal("ca");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("true");
        rightSide.addTerminal("true");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("false");
        rightSide.addTerminal("false");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("ELEMENTO_ASIGNADO", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("TERMINAL_INTERMEDIO");
        rightSide.addElement("OPERACION'");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("OPERACION", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("+");
        rightSide.addElement("TERMINAL_INTERMEDIO");
        rightSide.addElement("OPERACION'");
        rightSide.addTerminal("+");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("ε");
        rightSide.addTerminal(")");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("-");
        rightSide.addElement("TERMINAL_INTERMEDIO");
        rightSide.addElement("OPERACION'");
        rightSide.addTerminal("-");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("OPERACION'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("TERMINAL");
        rightSide.addElement("TERMINAL_INTERMEDIO'");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("TERMINAL_INTERMEDIO", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("ε");
        rightSide.addTerminal("+");
        rightSide.addTerminal("-");
        rightSide.addTerminal(")");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("*");
        rightSide.addElement("TERMINAL");
        rightSide.addElement("TERMINAL_INTERMEDIO'");
        rightSide.addTerminal("*");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("/");
        rightSide.addElement("TERMINAL");
        rightSide.addElement("TERMINAL_INTERMEDIO'");
        rightSide.addTerminal("/");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("TERMINAL_INTERMEDIO'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("id");
        rightSide.addTerminal("id");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("di");
        rightSide.addTerminal("di");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("(");
        rightSide.addElement("OPERACION");
        rightSide.addElement(")");
        rightSide.addTerminal("(");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("TERMINAL", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("if");
        rightSide.addElement("(");
        rightSide.addElement("SI1'");
        rightSide.addTerminal("if");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("SI1", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("EXPRESION");
        rightSide.addElement(")");
        rightSide.addElement("SI1''");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("ca");
        rightSide.addTerminal("true");
        rightSide.addTerminal("false");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("SI1'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("{");
        rightSide.addElement("INSTRUCCIONES_OPTATIVAS");
        rightSide.addElement("}");
        rightSide.addTerminal("{");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("SI1''", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("els");
        rightSide.addElement("INS_OPT");
        rightSide.addTerminal("els");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("SI2", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("DECLARACION_FOR");
        rightSide.addElement(";");
        rightSide.addElement("PARA2");
        rightSide.addTerminal("int");
        rightSide.addTerminal("flt");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("PARA1", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("EXPRESION");
        rightSide.addElement(";");
        rightSide.addElement("PARA3");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("ca");
        rightSide.addTerminal("true");
        rightSide.addTerminal("false");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("PARA2", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("OPERACION");
        rightSide.addElement(")");
        rightSide.addElement("PARA4");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("PARA3", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("{");
        rightSide.addElement("INSTRUCCIONES_OPTATIVAS");
        rightSide.addElement("}");
        rightSide.addTerminal("{");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("PARA4", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("{");
        rightSide.addElement("INSTRUCCIONES_OPTATIVAS");
        rightSide.addElement("}");
        rightSide.addTerminal("{");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("INS_OPT", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("EXPRESION");
        rightSide.addElement(")");
        rightSide.addElement("PARA4");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("ca");
        rightSide.addTerminal("true");
        rightSide.addTerminal("false");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("MIENTRAS", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("ELEMENTO_ASIGNADO");
        rightSide.addElement("EXPRESION'");
        rightSide.addTerminal("id");
        rightSide.addTerminal("di");
        rightSide.addTerminal("ca");
        rightSide.addTerminal("true");
        rightSide.addTerminal("false");
        rightSide.addTerminal("(");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("EXPRESION", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("RELACIONAL");
        rightSide.addElement("ELEMENTO_ASIGNADO");
        rightSide.addElement("EXPRESION''");
        rightSide.addTerminal(":=");
        rightSide.addTerminal(":>");
        rightSide.addTerminal(":<");
        rightSide.addTerminal(":*");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("EXPRESION'", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("LOGICO");
        rightSide.addElement("ELEMENTO_ASIGNADO");
        rightSide.addElement("EXPRESION'");
        rightSide.addTerminal("and");
        rightSide.addTerminal("or");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("EXPRESION''", rightSidesOfRule);
        table.add(gramaticRule);


        rightSide = new RightSide();
        rightSide.addElement(":=");
        rightSide.addTerminal(":=");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement(":>");
        rightSide.addTerminal(":>");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement(":<");
        rightSide.addTerminal(":<");
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement(":*");
        rightSide.addTerminal(":*");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("RELACIONAL", rightSidesOfRule);
        table.add(gramaticRule);

        rightSide = new RightSide();
        rightSide.addElement("and");
        rightSide.addTerminal("and");
        rightSidesOfRule = new ArrayList<>();
        rightSidesOfRule.add(rightSide);
        rightSide = new RightSide();
        rightSide.addElement("or");
        rightSide.addTerminal("or");
        rightSidesOfRule.add(rightSide);
        gramaticRule = new GramaticRule("LOGICO", rightSidesOfRule);
        table.add(gramaticRule);
    }

    public ArrayList<String> getDerivationOfRule(String leftSide, String terminal) {
        for(GramaticRule gramaticRule : table) {
            if(gramaticRule.getLeftSide().equals(leftSide)) {
                for(RightSide rightSide : gramaticRule.getRightSides()) {
                    if(rightSide.getTerminals().contains(terminal)) {
                        return rightSide.getRightSide();
                    }
                }
            }
        }
        if(rulesWithEmptyElement.contains(leftSide)) {
            ArrayList<String> empty = new ArrayList();
            empty.add("ε");
            return empty;
        }
        return null;
    }

    public ArrayList<GramaticRule> getTable() {
        return table;
    }

    public void setTable(ArrayList<GramaticRule> table) {
        this.table = table;
    }
}
