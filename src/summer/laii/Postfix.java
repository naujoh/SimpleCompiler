package summer.laii;

import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {

        System.out.println("Algebraic expression ");
        Scanner read = new Scanner(System.in);

        String expr = debugOutput(read.nextLine());
        String[] arrayInfix = expr.split(" ");

        Stack <String> entryStack = new Stack <String>();
        Stack <String> temporaryStack = new Stack <String>();
        Stack <String> outStack = new Stack <String>();

        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            entryStack.push(arrayInfix[i]);
        }

        try {
            while (!entryStack.isEmpty()) {
                switch (preference(entryStack.peek())){
                    case 1:
                        temporaryStack.push(entryStack.pop());
                        break;
                    case 3:
                    case 4:
                        while(preference(temporaryStack.peek()) >= preference(entryStack.peek())) {
                            outStack.push(temporaryStack.pop());
                        }
                        temporaryStack.push(entryStack.pop());
                        break;
                    case 2:
                        while(!temporaryStack.peek().equals("(")) {
                            outStack.push(temporaryStack.pop());
                        }
                        temporaryStack.pop();
                        entryStack.pop();
                        break;
                    default:
                        outStack.push(entryStack.pop());
                }
            }

            String infix = expr.replace(" ", "");
            String postfix = outStack.toString().replaceAll("[\\]\\[,]", "");

            System.out.println("Infix expression: " + infix);
            System.out.println("Postfix expression: " + postfix);

        }catch(Exception ex){
            System.out.println("Error expression");
            System.err.println(ex);
        }
    }

    private static String debugOutput(String s) {
        s = s.replaceAll("\\s+", "");
        s = "(" + s + ")";
        String symbols = "+-*/()";
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            if (symbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            }else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    //Operators preference
    private static int preference(String op) {
        int preference = 99;
        if (op.equals("^")) preference = 5;
        if (op.equals("*") || op.equals("/")) preference = 4;
        if (op.equals("+") || op.equals("-")) preference = 3;
        if (op.equals(")")) preference = 2;
        if (op.equals("(")) preference = 1;
        return preference;
    }
}