

package summer.laii.LexicalAnalysis;

import summer.laii.Language;
import summer.laii.SymbolTable.Register;
import summer.laii.SymbolTable.Table;

import java.util.ArrayList;

public class Automaton {
    private Language language;
    private State sourceState;
    private ArrayList<State> nextStates;

    public ArrayList<Automaton> identifiersAutomaton;
    public ArrayList<Automaton> digitsAutomaton;
    public ArrayList<Automaton> stringAutomaton;

    public  Automaton(boolean createAutomatons) {
        if(createAutomatons) {
            language = new Language();
            createIdentifiersAutomaton();
            createDigitsAutomaton();
            createStringAutomaton();
        }
    }

    public Automaton() {
        nextStates = new ArrayList<>();
    }

    public void createIdentifiersAutomaton() {
        identifiersAutomaton = new ArrayList<>();
        Automaton automatonElements = new Automaton();
        automatonElements.sourceState = new State(0,false);
        automatonElements.nextStates.add(new State(1, language.getLowercaseAlphabet(), true));
        identifiersAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(1, true);
        automatonElements.nextStates.add(new State(1,language.getLowercaseAlphabet(), true));
        automatonElements.nextStates.add(new State(1,language.getUppercaseAlphabet(), true));
        automatonElements.nextStates.add(new State(1,language.getNumbers(), true));
        identifiersAutomaton.add(automatonElements);
    }

    public void createDigitsAutomaton() {
        digitsAutomaton = new ArrayList<>();
        ArrayList<String> transitionElements = new ArrayList<>();
        Automaton automatonElements = new Automaton();
        automatonElements.sourceState = new State(0, false);
        automatonElements.nextStates.add(new State(1, language.getNumbers(), true));
        digitsAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(1, true);
        automatonElements.nextStates.add(new State(1,language.getNumbers(), true));
        transitionElements.add(".");
        automatonElements.nextStates.add(new State(2,transitionElements,false));
        digitsAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(2, false);
        automatonElements.nextStates.add(new State(3,language.getNumbers(), true));
        digitsAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(3,true);
        automatonElements.nextStates.add(new State(3,language.getNumbers(), true));
        digitsAutomaton.add(automatonElements);
    }

    public void createStringAutomaton() {
        stringAutomaton = new ArrayList<>();
        ArrayList<String> transitionElements = new ArrayList<>();
        transitionElements.add("\"");
        Automaton automatonElements = new Automaton();
        automatonElements.sourceState = new State(0, false);
        automatonElements.nextStates.add(new State(1, transitionElements, false));
        stringAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(1, false);
        automatonElements.nextStates.add(new State(2, transitionElements, true));
        automatonElements.nextStates.add(new State(1, language.getLowercaseAlphabet(), false));
        automatonElements.nextStates.add(new State(1, language.getUppercaseAlphabet(), false));
        automatonElements.nextStates.add(new State(1, language.getNumbers(),false));
        automatonElements.nextStates.add(new State(1, language.getSpecialSymbolAlphabet(), false));
        stringAutomaton.add(automatonElements);
        automatonElements = new Automaton();
        automatonElements.sourceState = new State(2, true);
        automatonElements.nextStates = null;
        stringAutomaton.add(automatonElements);
    }

    public Register recognizeLexeme(String lexeme) {
        Register register = null;
        State resultingState = goThroughAutomaton(identifiersAutomaton, lexeme);
        if(resultingState.getStateNumber()!=-1 && resultingState.isFinal()) {
            //The lexeme is an identifier
            register = new Register();
            register.setToken(lexeme);
            register.setType("");
            register.setLength(0);
            register.setValue("");
            register.setCategory(Table.IDENTIFIER);
        } else {
            resultingState = goThroughAutomaton(digitsAutomaton, lexeme);
            if(resultingState.getStateNumber()!=-1 && resultingState.isFinal()) {
                //The lexeme is a digit
                register = new Register();
                register.setToken(lexeme);
                if(resultingState.getStateNumber()==1) {
                    register.setType("integer");
                    register.setLength(4);
                } else {
                    register.setType("floating");
                    register.setLength(8);
                }
                register.setValue("");
                register.setCategory(Table.DIGIT);
            } else {
                resultingState = goThroughAutomaton(stringAutomaton, lexeme);
                if(resultingState.getStateNumber()!=-1 && resultingState.isFinal()) {
                    //The lexeme is a string
                    register = new Register();
                    register.setToken(lexeme);
                    register.setType("string");
                    register.setLength(50);
                    register.setValue("");
                    register.setCategory(Table.STRING);
                }
            }
        }
        return register;
    }

    private State goThroughAutomaton (ArrayList<Automaton> automaton, String lexeme) {
        int lexemePointer = 0;
        State currentState = new State(0, false);
        String pointerValue;
        while(lexemePointer<lexeme.length()) {
            pointerValue = String.valueOf(lexeme.charAt(lexemePointer));
            currentState = getNextState(currentState.getStateNumber(), pointerValue, automaton);
            lexemePointer++;
        }
        return currentState;
    }

    /** Automaton structure
     *
     * [
     *  {sourceState, [{numberSate, [transition_elements], isFinal}, {numberSate, [transition_elements], isFinal}, ...]}
     *  {sourceState, [{numberSate, [transition_elements], isFinal}]}
     *  ...
     * ]
     */

    private State getNextState(int currentStateNumber, String lexemeChar, ArrayList<Automaton> automaton) {
        State nextState = new State(-1, false); // -1 indicates error state
        for(Automaton state : automaton) {
            if(currentStateNumber==state.sourceState.getStateNumber()) {
                if(state.nextStates!=null)
                    for(State s : state.nextStates) {
                        if(s.getTransitionElements().contains(lexemeChar)) {
                            nextState = s;
                            return nextState;
                        }
                    }
            }
        }
        return nextState;
    }
}
