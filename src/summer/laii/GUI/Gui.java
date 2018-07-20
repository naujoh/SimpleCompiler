package summer.laii.GUI;

import summer.laii.LexicalAnalysis.LexicalAnalyzer;
import summer.laii.LexicalAnalysis.Preprocessor;
import summer.laii.SyntacticAnalysis.SyntacticAnalyzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Gui extends JFrame implements KeyListener, ActionListener {
    private JTextArea taLineNumber, taSourceCode, taErrors, taSymbolTable, taProductionStack, taSyntacticTable;
    private Preprocessor preprocessor;
    private LexicalAnalyzer lexicalAnalyzer;
    private SyntacticAnalyzer syntacticAnalyzer;

    public Gui() {
        createMenuBar();
        setLayout(new BorderLayout());
        createMainPanel();
        setTitle("Compilador");
        setResizable(false);
        pack();
        setVisible(true);
        preprocessor = new Preprocessor();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Archivo");
        JMenu analysis = new JMenu("Analisis");
        JMenuItem open = new JMenuItem("Abrir");
        JMenuItem lexical = new JMenuItem("Lexico");
        JMenuItem syntactic = new JMenuItem("Sintactico");
        JMenuItem semantic = new JMenuItem("Semantico");
        file.add(open);
        analysis.add(lexical);
        analysis.add(syntactic);
        analysis.add(semantic);
        open.addActionListener(this);
        lexical.addActionListener(this);
        syntactic.addActionListener(this);
        semantic.setEnabled(false);
        menuBar.add(file);
        menuBar.add(analysis);
        setJMenuBar(menuBar);
    }

    private void createMainPanel() {
        int lineNumber = 1;
        JPanel pMain = new JPanel();
        JPanel pDown = new JPanel();
        JPanel pDownLeft = new JPanel();
        JPanel pSourceCode = new JPanel();
        JPanel pErrors = new JPanel();
        JPanel pSymbolTable = new JPanel();
        JPanel pProductionsStack = new JPanel();
        JPanel pSyntacticTable = new JPanel();
        JScrollPane spSourceCode = new JScrollPane(pSourceCode);
        JScrollPane spErrors = new JScrollPane(pErrors);
        JScrollPane spSymbolTable = new JScrollPane(pSymbolTable);
        JScrollPane spProductionsStack = new JScrollPane(pProductionsStack);
        JScrollPane spSyntacticTable = new JScrollPane(pSyntacticTable);
        taSourceCode = new JTextArea(30, 64);
        taLineNumber = new JTextArea(30, 3);
        taSymbolTable = new JTextArea(30, 85);
        taErrors = new JTextArea(6, 58);
        taProductionStack = new JTextArea(10, 90);
        taSyntacticTable = new JTextArea(16, 100);
        taLineNumber.append(lineNumber+"");
        taLineNumber.setEditable(false);
        taSourceCode.addKeyListener(this);
        taErrors.setEditable(false);
        taSymbolTable.setFont(new Font("monospaced", Font.PLAIN, 12));
        taSymbolTable.setEditable(false);
        taProductionStack.setFont(new Font("monospaced", Font.PLAIN, 12));
        taProductionStack.setEditable(false);
        taSyntacticTable.setFont(new Font("monospaced", Font.PLAIN, 12));
        taSyntacticTable.setEditable(false);
        pSourceCode.add(taLineNumber);
        pSourceCode.add(taSourceCode);
        pErrors.add(taErrors);
        pSymbolTable.add(taSymbolTable);
        pProductionsStack.add(taProductionStack);
        pSyntacticTable.add(taSyntacticTable);
        pDownLeft.setLayout(new BorderLayout());
        pDownLeft.add(spErrors, BorderLayout.CENTER);
        pDownLeft.add(spProductionsStack, BorderLayout.SOUTH);
        pDown.setLayout(new BorderLayout());
        pDown.add(pDownLeft, BorderLayout.CENTER);
        pDown.add(spSyntacticTable, BorderLayout.EAST);
        pMain.setLayout(new BorderLayout());
        pMain.add(spSourceCode, BorderLayout.CENTER);
        pMain.add(spSymbolTable, BorderLayout.EAST);
        pMain.add(pDown, BorderLayout.SOUTH);
        add(pMain, BorderLayout.CENTER);
    }

    private void countRows() {
        taLineNumber.setText("1");
        int lineCount = taSourceCode.getLineCount();
        for(int i=2; i<=lineCount; i++) { taLineNumber.setText(taLineNumber.getText()+"\n"+i); }
    }

    private void openFile() {
        taSourceCode.setText("");
        Preprocessor preprocessor = new Preprocessor();
        JFileChooser fileChooser = new JFileChooser();
        File file;
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showOpenDialog(this);
        file = fileChooser.getSelectedFile();
        if((file == null) || (file.getName().equals(""))) {
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido",
                    "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE );
        }
        taSourceCode.setText(preprocessor.getFileContent(file));
        countRows();
    }

    private void lexicalAnalysis() {
        lexicalAnalyzer = new LexicalAnalyzer();
        taSymbolTable.setText("");
        preprocessor.setFileContent(taSourceCode.getText());
        lexicalAnalyzer.classifyLexemes(preprocessor.getLexemes());
        taSymbolTable.setText(lexicalAnalyzer.getSymbolTable().readDataFromTable());
        taErrors.setText(lexicalAnalyzer.getErrorsFound());
    }

    private void syntacticAnalysis() {
        String errors;
        syntacticAnalyzer = new SyntacticAnalyzer(lexicalAnalyzer.getSymbolTable().getSourceCode());
        taSyntacticTable.setText("");
        taSyntacticTable.setText(syntacticAnalyzer.printSyntacticTable());
        syntacticAnalyzer.analyze();
        errors = taErrors.getText() + syntacticAnalyzer.getErrors();
        taErrors.setText(errors);
        taProductionStack.setText("");
        taProductionStack.setText(syntacticAnalyzer.getStackTransition());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Abrir":
                openFile();
                break;
            case "Lexico":
                lexicalAnalysis();
                break;
            case "Sintactico":
                lexicalAnalysis();
                syntacticAnalysis();
                break;
            case "Semantico":
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.isControlDown() || e.getKeyCode()==10 || e.getKeyCode()==8 || e.getKeyCode()==127){
            countRows();
        }
    }
}
