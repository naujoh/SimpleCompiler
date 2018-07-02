package summer.laii;

import summer.laii.GUI.GUI;
import summer.laii.LexicalAnalysis.LexicalAnalyzer;
import summer.laii.LexicalAnalysis.Preprocessor;

import java.io.File;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
	/* write your code here
        Table testTable = new Table();

        //Insert data
        Register testReg = new Register();
        testReg.setToken("int");
        testReg.setType("");
        testReg.setLength(4);
        testReg.setValue("");
        testReg.setCategory("PR");
        testTable.insertData(testReg);

        testReg.setToken("x");
        testReg.setType("integer");
        testReg.setLength(4);
        testReg.setValue("");
        testReg.setCategory("ID");
        testTable.insertData(testReg);

        testReg.setToken("=");
        testReg.setType("");
        testReg.setLength(0);
        testReg.setValue("");
        testReg.setCategory("OP");
        testTable.insertData(testReg);

        //Retrieve data
        Register reg = testTable.getData("x");
        if(reg!=null)
            System.out.println(reg.getKey() + "\n" + reg.getLength() + "\n" + reg.getToken());
        else
            System.out.println("Data not found");
    */
	    /*File file = new File("test");
        Preprocessor preprocessor = new Preprocessor();
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        preprocessor.getFileContent(file);
        lexicalAnalyzer.classifyLexemes(preprocessor.getLexemes());
        lexicalAnalyzer.printData();
        /*for(String s : preprocessor.getLexemes()) {
            System.out.println(s);
        }*/
        GUI gui=new GUI();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

