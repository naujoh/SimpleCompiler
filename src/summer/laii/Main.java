package summer.laii;

import summer.laii.LexicalAnalysis.Preprocessor;

import java.io.File;

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
	    File file = new File("test");
        Preprocessor preprocessor = new Preprocessor();
        preprocessor.getFileContent(file);
        for(String s : preprocessor.getLexemes()) {
            System.out.println(s);
        }
    }
}

