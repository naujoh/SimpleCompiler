/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summer.laii.GUI;

/**
 *
 * @author eric-apk
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import summer.laii.LexicalAnalysis.LexicalAnalyzer;
import summer.laii.LexicalAnalysis.Preprocessor;

public class GUI extends JFrame implements KeyListener, ActionListener{

    Preprocessor preprocessor =new Preprocessor();
    JMenuBar menu;
    JMenu archivo, analisis;
    JMenuItem nuevo, abrir, guardar, salir, lexico, sintactico, semantico;
    JPanel pCodigo, pSalida, pMostrar, pBoton, pPrincipal, pAuxiliar;
    JTextArea taNumero, taLinea, taSalida, taMostrar;
    JScrollPane spCodigo, spMostrar, spErrores;
    private JButton bCerrar;
    JFileChooser fileChooser;
    File file;
    ArrayList<String> listaLexemas =new ArrayList<>();
    String textoAuxiliar;
    int numeroLinea=1, accion;
    boolean cambios=false;
        
    public GUI(){
        setMenu();
        setPanelPrincipal();
        setPanelAuxiliar();
        setJMenuBar(menu);
        setLayout(new BorderLayout());
        add(pPrincipal,BorderLayout.CENTER);
        setTitle("Compilador");
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    /**
     *  Instancia los elementos de la barra de menu
     */
    public void setMenu(){
        menu=new JMenuBar();
        //Componentes de la barra de menu
        archivo=new JMenu("Archivo");
        analisis=new JMenu("Análisis");
        //Items - menu archivo
        nuevo=new JMenuItem("Nuevo", new ImageIcon("Nuevo.png"));
        nuevo.addActionListener(this);
        abrir=new JMenuItem("Abrir", new ImageIcon("Abrir.png"));
        abrir.addActionListener(this);
        guardar=new JMenuItem("Guardar", new ImageIcon("Guardar1.png"));
        guardar.addActionListener(this);
        salir=new JMenuItem("Salir");
        salir.addActionListener(this);
        
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(salir);
        //Items menu analisis
        lexico=new JMenuItem("Léxico");
        lexico.addActionListener(this);
        sintactico=new JMenuItem("Sintáctico");
        sintactico.addActionListener(this);
        sintactico.setEnabled(false);
        semantico=new JMenuItem("Semántico");
        semantico.addActionListener(this);
        semantico.setEnabled(false);
        analisis.add(lexico);
        analisis.add(sintactico);
        analisis.add(semantico);
        //Añadir componentes a la barra de menu
        menu.add(archivo);
        menu.add(analisis);
    }
    
    /**
     * Intanciar los elementos que conforman el panel principal
     */
    public void setPanelPrincipal(){
                pPrincipal=new JPanel();
        pCodigo=new JPanel();
        pSalida=new JPanel();

        taNumero=new JTextArea(30, 3);
        taNumero.append(numeroLinea+"");
        taNumero.setEditable(false);
        taLinea=new JTextArea(30, 70);
        taLinea.addKeyListener(this);
        taSalida=new JTextArea(4, 74);
        taSalida.setEditable(false);
        spCodigo=new JScrollPane(pCodigo);
        spErrores=new JScrollPane(pSalida);
        //Se agregan los componentes al panel principal
        pCodigo.add(taNumero);
        pCodigo.add(taLinea);
        pSalida.add(taSalida);
        pPrincipal.setLayout(new BorderLayout());
        pPrincipal.add(spCodigo, BorderLayout.CENTER);
        pPrincipal.add(spErrores, BorderLayout.SOUTH);
    }
    
    /**
     * Instancia los elementos que conforman el panel auxiliar
     */    
    public void setPanelAuxiliar(){
        /**
         * INSTANCIA DE LOS PANELES
         */
        pMostrar=new JPanel();
        pBoton=new JPanel();
        pAuxiliar=new JPanel();
        /**
         * INSTANCIA DE LOS TEXTAREA Y EL SRCOLLPANE
         */
        taMostrar=new JTextArea(30, 45);
        taMostrar.setEditable(false);
        bCerrar=new JButton("Cerrar");
        bCerrar.addActionListener(this);
        spMostrar=new JScrollPane(taMostrar);
        /**
         * SE AGREGAN LOS COMPONENTES AL PANEL AUXILIAR
         */
        pMostrar.add(spMostrar);
        pBoton.add(bCerrar);
        pAuxiliar.setLayout(new BorderLayout());
        pAuxiliar.add(pMostrar, BorderLayout.CENTER);
        pAuxiliar.add(pBoton, BorderLayout.SOUTH);
    }
    
    /**
     * MÉTODO QUE ABRE EL EXPLORADOR DE ARCHIVOS PARA SELECCIONAR UNO.
     * EL ARCHIVO ES ENVIADO A LA CLASE PREPROCESO PARA LEERLO Y POSTERIORMENTE SER MOSTRADO EN EL TEXTAREA
     */
    public void abrirArchivo(){
        taLinea.setText("");
        fileChooser = new JFileChooser(); 
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES ); 
        int resultado=fileChooser.showOpenDialog(this); 

        if(resultado==JFileChooser.CANCEL_OPTION) 
            System.exit(1); 
        file=fileChooser.getSelectedFile(); 
        if((file==null) || (file.getName().equals(""))){ 
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido",
                    "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE );
            System.exit(1);
        }
        taLinea.setText(preprocessor.getFileContent(file));
        contarFilas();
    }
    
    /**
     * MÉTODO QUE LIMPIA EL TEXTAREA DEL CÓDIGO PARA TRABAJAR CON UN NUEVO ARCHIVO
     */
    public void nuevoArchivo(){
        taLinea.setText("");
        contarFilas();
    }
    /**
     * MÉTODO QUE AGREGA EL PANEL AUXILIAR Y UN CONTENIDO REDIMENSIONANDO EL FRAME
     * @param archivo - CONTENIDO QUE SERÁ MOSTRADO EN EL PANEL
     */
    public void abrirPanelAuxiliar(String archivo){
        file=new File(archivo);
        textoAuxiliar=taLinea.getText();
        taMostrar.setText("");
        remove(pAuxiliar);
        add(pAuxiliar,BorderLayout.EAST);
        taLinea.setText("");
        taNumero.setText("");
        validate();
        repaint();
        pack();
        taLinea.setText(textoAuxiliar);
        contarFilas();
    }
    
    /**
     * MÉTODO QUE REMUEVE EL PANEL AUXILIAR REDIMENSIONANDO EL FRAME
     */
    public void cerrarPanelAuxiliar(){
        textoAuxiliar=taLinea.getText();
        taLinea.setText("");
        taNumero.setText("");
        remove(pAuxiliar);
        repaint();
        pack();
        taLinea.setText(textoAuxiliar);
        contarFilas();
    }
    
    /**
     * MÉTODO QUE CUENTA LAS FILAS DEL TEXTAREA DE CÓDIGO, PARA LLEVAR EL CONTROL DEL NÚMERO DE LÍNEA
     */
    public void contarFilas(){
        int filas=taLinea.getLineCount();
        taNumero.setText("1");
        for(int i=2; i<=filas;i++){
            taNumero.setText(taNumero.getText()+"\n"+i);
        }
    }

    public String getCodigo(){
        return taLinea.getText();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        cambios=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if(e.isControlDown() || e.getKeyCode()==10 || e.getKeyCode()==8 || e.getKeyCode()==127){
            contarFilas();            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "NUEVO"
         */
        if(command.equals("Nuevo")){
            if(cambios==true){
                accion=JOptionPane.showOptionDialog(null,
                        "\n        El archivo ha sido modificado \n ¿Desea guardar el archivo antes de salir? \n\n",
                        "Archivo",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,
                        new Object[] {"Si","No"},"No");
                if(accion==1){
                    nuevoArchivo();
                }else{
                    //guardarArchivo();
                    nuevoArchivo();
                }                
            }else{
                nuevoArchivo();
            }            
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "GUARDAR"
         */
        if(command.equals("Guardar")){
            JOptionPane.showMessageDialog(null,"We keep coding this function.\nWait for an update");
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "ABRIR"
         */
        if(command.equals("Abrir")){
            if(cambios==true){
                accion=JOptionPane.showOptionDialog(null,
                        "\nEl archivo ha sido modificado \n ¿Desea guardar el archivo antes de salir? \n\n", "Archivo",
                        JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,new Object[] {"Si","No"},"No");
                if(accion==1){
                    abrirArchivo();
                }else{
                    //guardarArchivo();
                    abrirArchivo();
                }                
            }else{
                abrirArchivo();
            }
            cambios=false;
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "SALIR"
         */
        if(command.equals("Salir")){
            System.exit(0);
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "LÉXICO"
         */
        if(command.equals("Léxico")){
            taMostrar.setText("");
            textoAuxiliar="";
            remove(pAuxiliar);
            preprocessor.setFileContent(taLinea.getText());
            listaLexemas= preprocessor.getLexemes();
            //Pasar la lista de lexemas al analisis lexico
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
            lexicalAnalyzer.classifyLexemes(listaLexemas);
            textoAuxiliar = lexicalAnalyzer.getSymbolTable().readDataFromTable();
            taSalida.setText(lexicalAnalyzer.getErrorsFound());
            add(pAuxiliar,BorderLayout.EAST);
            String algo=taLinea.getText();
            taLinea.setText("");
            taNumero.setText("");
            validate();
            repaint();
            pack();
            taLinea.setText(algo);
            contarFilas();
            taMostrar.setText(textoAuxiliar);
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "SINTÁCTICO"
         */
        if(command.equals("Sintáctico")){
            
        }
        /**
         * INSTRUCCIONES CUANDO SE SELECCIONA LA OPCIÓN "SEMÁNTICO"
         */
        if(command.equals("Semántico")){
            
        }
        /**
         * INSTRUCCIONES CUANDO SE DA CLIC EN EL BOTÓN "CERRAR"
         */
        if(command.equals("Cerrar")){
            cerrarPanelAuxiliar();
        }
    }
}