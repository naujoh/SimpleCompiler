package summer.laii.LexicalAnalysis;

import summer.laii.Language;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocessor {
    private String fileContent;

    public Preprocessor() { fileContent = ""; }

    /** Open a file that resides in file system and extract his content
     *
     * @param file object that represents file in file system
     * @return a string that has the content of the file
     */

    public String getFileContent(File file) {
        String line;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();
            while(line!=null){
                fileContent += line + "\n";
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch(IOException e) { JOptionPane.showMessageDialog(null, "Failed to open file"); }
        return fileContent;
    }

    /** Obtain list of lexemes identified in source code with his line number attached
     *
     * @return an arraylist with the identified lexemes
     */

    public ArrayList<String> getLexemes() {
        Language language = new Language();
        Character character;
        String lexeme = "";
        ArrayList<String> lexemes = new ArrayList<>();
        String[] lines = getProcessedString().split("\n");
        for(String line : lines) {
            String[] lineNumberElement = getLineNumber(line);
            for(int i=Integer.parseInt(lineNumberElement[1]);i<line.length();i++) {
                character = line.charAt(i);
                if(character!=' ') {
                    if(language.getDelimiters().contains(character.toString()) ||
                       language.getOperators().contains(character.toString())  ||
                       language.getSpecialElements().contains(character.toString())) {
                        if(!lexeme.equals("")) {
                            lexemes.add(lineNumberElement[0]+" "+lexeme);
                            lexeme="";
                        }
                        if(character==':') {
                            lexeme += character;
                            i++;
                            if(i<line.length()) {
                                if(line.charAt(i)=='=' || line.charAt(i) == '<' ||
                                   line.charAt(i)=='>' || line.charAt(i) == '*') {
                                    lexeme += line.charAt(i);
                                    lexemes.add(lineNumberElement[0]+" "+lexeme);
                                    lexeme="";
                                } else { i--; }
                            }
                        } else { lexemes.add(lineNumberElement[0]+" "+character); }
                    } else {
                        if(character=='"') {
                            lexeme += character;
                            i++;
                            while(i<line.length()) {
                                if(line.charAt(i)=='"') {
                                    lexeme += character;
                                    break;
                                } else {
                                    lexeme += line.charAt(i);
                                }
                                i++;
                            }
                            lexemes.add(lineNumberElement[0]+" "+lexeme);
                            lexeme="";
                        } else lexeme += character;
                    }
                } else if(!lexeme.equals("")) {
                    lexemes.add(lineNumberElement[0]+" "+lexeme);
                    lexeme="";
                }
            }
            if(!lexeme.equals("")) {
                lexemes.add(lineNumberElement[0]+" "+lexeme);
                lexeme="";
            }
        }
        return lexemes;
    }

    /** Method to extract the line number of a line of the source code
     *
     * @param line that going to be analyzed
     * @return string array with the number analyzed and the number of digits of this number
     */

    private String[] getLineNumber(String line) {
        String[] lineNumberElement = new String[2];
        int numberOfDigits = 0;
        String lineNumber = "";
        for(int i = 0; i<line.length(); i++) {
            if(line.charAt(i)==' ') {
                lineNumberElement[0] = lineNumber;
                lineNumberElement[1] = String.valueOf(numberOfDigits);
                return lineNumberElement;
            } else {
                lineNumber += line.charAt(i);
                numberOfDigits++;
            }
        }
        return lineNumberElement;
    }

    /** Remove empty lines and with a tabulation char and comments from source code
     *
     * @return processed string to be analyzed by the parser,
     * the string includes line number at the beginning of each line
     */
    private String getProcessedString() {
        String processedString = "", lineWithOutComment;
        String[] lines;
        if(!fileContent.equals("")) {
            lines = fileContent.split("\n");
            for(int i=0; i<lines.length; i++) {
                if(!lines[i].equals("") && !lines[i].equals("\t")) {
                    lineWithOutComment = findCommentaryInLine(lines[i]);
                    if (!lineWithOutComment.equals(""))
                        processedString += (i + 1) + " " + lineWithOutComment.trim() + "\n";
                }
            }
        }
        return processedString;
    }

    /** Find comments on a line and remove them, if hole line is a comment then removes that line
     *
     * @param line to be analyzed to find comments on it
     * @return empty string if the line is a comment, all the line if a comment wasn't found or
     * part of the line that isn't a comment
     */

    private String findCommentaryInLine(String line) {
        String lineWithOutComment="";
        for(int i=0; i<line.length(); i++) {
            if(line.charAt(i)==':')
                if(i+1<line.length())
                    if(line.charAt(i+1)=='v') {
                        break;
                    }
            lineWithOutComment += line.charAt(i);
        }
        return lineWithOutComment;
    }

    public void setFileContent (String data) {
        this.fileContent = data;
    }
    public void fileContent(String fileContent) {
        this.fileContent=fileContent;
    }
}
