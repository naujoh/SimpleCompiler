package summer.laii.LexicalAnalysis;

import summer.laii.Language;
import summer.laii.SymbolTable.Register;
import summer.laii.SymbolTable.Table;

import java.util.ArrayList;

public class LexicalAnalyzer {
    private Table symbolTable;
    private Automaton automaton;
    private ArrayList<String> lexicalErrors;

    public LexicalAnalyzer() {
        automaton = new Automaton(true);
        symbolTable = new Table();
        lexicalErrors = new ArrayList<>();

    }

    public void classifyLexemes(ArrayList<String> lexemes) {
        Register tableRegister;
        Language language = new Language();
        boolean dataTypeFound = false, constantDefinition = false;
        String dataTypeLex = "";
        for (String lexeme : lexemes) {
            String lexWithOutLineNumber= removeLineNumberFromLexeme(lexeme);
            //1. Verify if the lexeme belongs to some element defined in the language
            tableRegister = new Register();
            tableRegister.setType("");
            tableRegister.setLength(0);
            tableRegister.setValue("");
            tableRegister.setConstant("");
            if(language.getReserved_words().contains(lexWithOutLineNumber)) {
                tableRegister.setToken(lexWithOutLineNumber);
                tableRegister.setCategory(Table.RESERVEDWORD);
                symbolTable.insertData(tableRegister);
            } else if (language.getDelimiters().contains(lexWithOutLineNumber)) {
                tableRegister.setToken(lexWithOutLineNumber);
                tableRegister.setCategory(Table.DELIMITER);
                symbolTable.insertData(tableRegister);
            } else if (language.getOperators().contains(lexWithOutLineNumber)) {
                tableRegister.setToken(lexWithOutLineNumber);
                tableRegister.setCategory(Table.OPERATOR);
                symbolTable.insertData(tableRegister);
            }
            //2. If not then verify if the lexeme could be recognized by the automatons
            else {
                tableRegister = automaton.recognizeLexeme(lexWithOutLineNumber);
                if(tableRegister==null) {
                    if(!checkIfIsAString(lexWithOutLineNumber, getLineNumberOfLexeme(lexeme)))
                        lexicalErrors.add (
                            String.format("Error lexico, el elemento %s en la linea %s no pudo ser reconocido",
                            lexWithOutLineNumber, getLineNumberOfLexeme(lexeme)));
                } else {
                    if(tableRegister.getCategory().equals("ID")) {
                        if(constantDefinition)
                            tableRegister.setConstant("const");
                        else
                            tableRegister.setConstant("var");

                        if(dataTypeFound) {
                            switch (dataTypeLex) {
                                case "int":
                                    tableRegister.setType("integer");
                                    tableRegister.setLength(4);
                                    break;
                                case "flt":
                                    tableRegister.setType("float");
                                    tableRegister.setLength(8);
                                    break;
                                case "str":
                                    tableRegister.setType("string");
                                    tableRegister.setLength(50);
                                    break;
                                case "bln":
                                    tableRegister.setType("boolean");
                                    tableRegister.setLength(1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    symbolTable.insertData(tableRegister);
                }
            }
            if(lexWithOutLineNumber.equals("def")) { constantDefinition = true; }
            if(lexWithOutLineNumber.equals("int") || lexWithOutLineNumber.equals("flt") ||
               lexWithOutLineNumber.equals("str") || lexWithOutLineNumber.equals("bln")) {
                dataTypeFound = true;
                dataTypeLex =lexWithOutLineNumber;
            }
            if(lexWithOutLineNumber.equals(";")) { dataTypeFound = false; constantDefinition = false;}
        }
    }

    private boolean checkIfIsAString(String lexeme, String lineNumber) {
        boolean isAString = false;
        if(lexeme.charAt(0)=='"') {
            String errorLexeme="", lexemeWithOutError="";
            int pointer=0;
            isAString = true;
            for(int i=0; i<lexeme.length(); i++) {
                if(lexeme.charAt(i)==' ') {
                    pointer=i+1;
                    break;
                } else errorLexeme+=lexeme.charAt(i);
            }
            lexicalErrors.add(
                String.format("Error lexico, el elemento %s en la linea %s no pudo ser reconocido",
                               errorLexeme, lineNumber));
            if(pointer!=0) {
                for (int i = pointer; i < lexeme.length(); i++) {
                    lexemeWithOutError += lexeme.charAt(i);
                }
                Preprocessor p = new Preprocessor();
                p.setFileContent(lexemeWithOutError);
                ArrayList<String> lexemesOfLexeme = p.getLexemes();
                classifyLexemes(lexemesOfLexeme);
            }
        }
        return isAString;
    }

    private String getLineNumberOfLexeme(String lexeme) {
        String lineNumber="";
        for(int i=0; i<lexeme.length();i++) {
            if(lexeme.charAt(i) == ' ') { break; } else { lineNumber+=lexeme.charAt(i); }
        }
        return lineNumber;
    }

    private String removeLineNumberFromLexeme(String lexeme) {
        String lexemeWithOutLineNumber="";
        int pointer;
        for(pointer=0; pointer<lexeme.length(); pointer++) {
            if(lexeme.charAt(pointer)==' ') {
                pointer++;
                break;
            }
        }
        for(int i=pointer; i<lexeme.length(); i++) { lexemeWithOutLineNumber+=lexeme.charAt(i); }
        return lexemeWithOutLineNumber;
    }

    public Table getSymbolTable() {
        return symbolTable;
    }

    public String getErrorsFound() {
        String errors = "";
        for(String s : lexicalErrors) {
            errors+=s+"\n";
        }
         return errors;
    }
}
