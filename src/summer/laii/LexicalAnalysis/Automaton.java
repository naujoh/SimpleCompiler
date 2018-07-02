/** Automaton structure
 *
 * [
 *  {sourceState, [{numberSate, [transition_elements], iFinal}, {numberSate, [transition_elements], iFinal}, ...]}
 *  {sourceState, [{numberSate, [transition_elements], iFinal}]}
 *  ...
 * ]
 */

package summer.laii.LexicalAnalysis;

import summer.laii.Language;
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
            createIdentifiersAutomaton();
            createDigitsAutomaton();
            createStringAutomaton();
        }
    }

    public Automaton() {
        language = new Language();
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
        automatonElements.nextStates.add(new State(1, language.getNumbers(), false));
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

    public int getNextState(int currentState, char lexemeChar, ArrayList<Automaton> automaton) {
        int nextState = -1; //indicates error state
        //for(Automaton a : automaton) {
        //    if(currentState==a.)
        //}
        return 0;
    }
}
