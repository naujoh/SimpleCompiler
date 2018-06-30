package summer.laii;

import java.util.ArrayList;

public class Language {
    private ArrayList<String> reserved_words;
    private ArrayList<String> delimiters;
    private ArrayList<String> operators;
    private ArrayList<String> special_elements;
    private ArrayList<String> lowercaseAlphabet;
    private ArrayList<String> uppercaseAlphabet;
    private ArrayList<String> numbers;
    private ArrayList<String> specialSymbolAlphabet;

    public Language(){
        setReservedWords();
        setDelimiters();
        setOperators();
        setSpecialElements();
        createLowercaseAlphabet();
        createUppercaseAlphabet();
        createNumbersList();
        createSpecialSymbolsAlphabet();
    }

    private void setReservedWords(){
        reserved_words = new ArrayList<>();
        reserved_words.add("int");
        reserved_words.add("flt");
        reserved_words.add("str");
        reserved_words.add("bln");
        reserved_words.add("if");
        reserved_words.add("els");
        reserved_words.add("for");
        reserved_words.add("while");
        reserved_words.add("def");
        reserved_words.add("fun");
        reserved_words.add("rtn");
        reserved_words.add("and");
        reserved_words.add("or");
        reserved_words.add("not");
        reserved_words.add("open");
        reserved_words.add("close");
    }

    private void setDelimiters() {
        delimiters = new ArrayList<>();
        delimiters.add("{");
        delimiters.add("}");
        delimiters.add("(");
        delimiters.add(")");
        delimiters.add("_");
        delimiters.add(";");
        delimiters.add(",");
        delimiters.add(":v");
        delimiters.add("\"");
    }

    private void setOperators() {
        operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("=");
        operators.add(":=");
        operators.add(":<");
        operators.add(":>");
        operators.add(":*");
    }

    private void createLowercaseAlphabet() {
        lowercaseAlphabet = new ArrayList<>();
        lowercaseAlphabet.add("a");
        lowercaseAlphabet.add("b");
        lowercaseAlphabet.add("c");
        lowercaseAlphabet.add("d");
        lowercaseAlphabet.add("e");
        lowercaseAlphabet.add("f");
        lowercaseAlphabet.add("g");
        lowercaseAlphabet.add("h");
        lowercaseAlphabet.add("i");
        lowercaseAlphabet.add("j");
        lowercaseAlphabet.add("k");
        lowercaseAlphabet.add("l");
        lowercaseAlphabet.add("m");
        lowercaseAlphabet.add("n");
        lowercaseAlphabet.add("o");
        lowercaseAlphabet.add("p");
        lowercaseAlphabet.add("q");
        lowercaseAlphabet.add("r");
        lowercaseAlphabet.add("s");
        lowercaseAlphabet.add("t");
        lowercaseAlphabet.add("u");
        lowercaseAlphabet.add("v");
        lowercaseAlphabet.add("w");
        lowercaseAlphabet.add("x");
        lowercaseAlphabet.add("y");
        lowercaseAlphabet.add("z");
    }

    private void createUppercaseAlphabet() {
        uppercaseAlphabet = new ArrayList<>();
        uppercaseAlphabet.add("A");
        uppercaseAlphabet.add("B");
        uppercaseAlphabet.add("C");
        uppercaseAlphabet.add("D");
        uppercaseAlphabet.add("E");
        uppercaseAlphabet.add("F");
        uppercaseAlphabet.add("G");
        uppercaseAlphabet.add("H");
        uppercaseAlphabet.add("I");
        uppercaseAlphabet.add("J");
        uppercaseAlphabet.add("K");
        uppercaseAlphabet.add("L");
        uppercaseAlphabet.add("M");
        uppercaseAlphabet.add("N");
        uppercaseAlphabet.add("O");
        uppercaseAlphabet.add("P");
        uppercaseAlphabet.add("Q");
        uppercaseAlphabet.add("R");
        uppercaseAlphabet.add("S");
        uppercaseAlphabet.add("T");
        uppercaseAlphabet.add("U");
        uppercaseAlphabet.add("V");
        uppercaseAlphabet.add("W");
        uppercaseAlphabet.add("X");
        uppercaseAlphabet.add("Y");
        uppercaseAlphabet.add("Z");
    }

    private void createNumbersList(){
        numbers = new ArrayList<>();
        numbers.add("0");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
    }

    private void createSpecialSymbolsAlphabet() {
        specialSymbolAlphabet = new ArrayList<>();
        specialSymbolAlphabet.add("!");
        specialSymbolAlphabet.add("@");
        specialSymbolAlphabet.add("#");
        specialSymbolAlphabet.add("$");
        specialSymbolAlphabet.add("%");
        specialSymbolAlphabet.add("^");
        specialSymbolAlphabet.add("&");
        specialSymbolAlphabet.add("*");
        specialSymbolAlphabet.add("?");
        specialSymbolAlphabet.add("(");
        specialSymbolAlphabet.add(")");
        specialSymbolAlphabet.add("{");
        specialSymbolAlphabet.add("}");
        specialSymbolAlphabet.add("[");
        specialSymbolAlphabet.add("]");
        specialSymbolAlphabet.add(" ");
        specialSymbolAlphabet.add("'");
    }

    private void setSpecialElements() {
        special_elements = new ArrayList<>();
        special_elements.add(":");
    }

    public ArrayList<String> getReserved_words() {
        return reserved_words;
    }

    public ArrayList<String> getDelimiters() {
        return delimiters;
    }

    public ArrayList<String> getOperators() {
        return operators;
    }

    public ArrayList<String> getSpecialElements() {
        return special_elements;
    }

    public ArrayList<String> getLowercaseAlphabet() {
        return lowercaseAlphabet;
    }

    public ArrayList<String> getUppercaseAlphabet() {
        return uppercaseAlphabet;
    }

    public ArrayList<String> getNumbers() {
        return numbers;
    }

    public ArrayList<String> getSpecialSymbolAlphabet() {
        return specialSymbolAlphabet;
    }
}
