package summer.laii.LexicalAnalysis;

import summer.laii.Language;
import summer.laii.SymbolTable.Register;
import summer.laii.SymbolTable.Table;

import java.util.ArrayList;

public class LexicalAnalyzer {
    private Table symbolTable;
    private Automaton automaton;

    //Tokens categories
    private static final String RESERVEDWORD = "PR";
    private static final String OPERATOR = "OP";
    private static final String DIGIT = "DI";
    private static final String DELIMITER = "DE";
    private static final String IDENTIFIER = "ID";
    private static final String STRING = "CA";

    public LexicalAnalyzer() {
        automaton = new Automaton(true);
        symbolTable = new Table();
    }

    public void clasifyLexemes(ArrayList<String> lexemes) {
        Register tableRegister;
        Language language = new Language();
        for (String lexeme : lexemes) {
            //1. Verify if the lexeme belongs to some element defined in the language
            tableRegister = new Register();
            if(language.getReserved_words().contains(lexeme)) {
                tableRegister.setToken(lexeme);
                tableRegister.setType("");
                tableRegister.setLength(0);
                tableRegister.setValue("");
                tableRegister.setCategory(RESERVEDWORD);
                symbolTable.insertData(tableRegister);
            } else if (language.getDelimiters().contains(lexeme)) {
                tableRegister.setToken(lexeme);
                tableRegister.setType("");
                tableRegister.setLength(0);
                tableRegister.setValue("");
                tableRegister.setCategory(DELIMITER);
                symbolTable.insertData(tableRegister);
            } else if (language.getOperators().contains(lexeme)) {
                tableRegister.setToken(lexeme);
                tableRegister.setType("");
                tableRegister.setLength(0);
                tableRegister.setValue("");
                tableRegister.setCategory(OPERATOR);
                symbolTable.insertData(tableRegister);
                symbolTable.insertData(tableRegister);
            }
            //2. If not then verify if the lexeme could be recognized by the automatons
            else {
                int sourceState = 0, nextState, lexemePointer;
                Character pointerValue;
                lexemePointer = readLineNumber(lexeme);
                while(lexemePointer<lexeme.length()) {
                    pointerValue = lexeme.charAt(lexemePointer);
                    nextState = automaton.getNextState(sourceState, pointerValue, automaton.digitsAutomaton);
                }
            }
        }
    }

    /** Move the pointer of the lexeme after the line number
     *
     * @param lexeme
     * @return an integer that represents pointer position
     */
    private int readLineNumber(String lexeme) {
        int pointer;
        for(pointer=0; pointer<lexeme.length(); pointer++) {
            if(lexeme.charAt(pointer)==' ') {
                pointer++;
                break;
            }
        }
        return pointer;
    }
}
