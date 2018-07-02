package summer.laii.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Table {
    private RandomAccessFile symbolTable, indexFile;
    private String sybolTableFilePath = "symbol_table", indexFilePath = "indexes";
    private HashTable hashTable;

    //Tokens categories
    public static final String RESERVEDWORD = "PR";
    public static final String OPERATOR = "OP";
    public static final String DIGIT = "DI";
    public static final String DELIMITER = "DE";
    public static final String IDENTIFIER = "ID";
    public static final String STRING = "CA";

    public Table() {
        try {
            File table = new File(sybolTableFilePath);
            File index = new File(indexFilePath);
            table.delete();
            table.createNewFile();
            index.delete();
            index.createNewFile();
            symbolTable = new RandomAccessFile(table, "rw");
            indexFile = new RandomAccessFile(index, "rw");
        } catch (IOException e) { e.printStackTrace(); }
        hashTable = new HashTable();
    }

    /** Insert a register to symbol table
     *
     * @param register Object to insert in symbol table
     */
    public void insertData(Register register) {
        long index = hashTable.getHash(register.getToken());
        try {
            indexFile.writeLong(index);
            symbolTable.seek(index*Register.reg_long);
            symbolTable.writeLong(index);
            symbolTable.writeChars(register.getToken());
            symbolTable.writeChars(register.getType());
            symbolTable.writeInt(register.getLength());
            symbolTable.writeChars(register.getValue());
            symbolTable.writeChars(register.getCategory());
        } catch (IOException e) { e.printStackTrace(); }
    }

    /** Get data from a register of the symbol table
     *
     * @param token to find register positon in symbol table
     * @return reg object with found data from symbol table
     */
    public Register getData(String token) {
        Register reg = new Register();
        reg.setToken(token);
        long index = hashTable.getHash(reg.getToken());
        try {
            symbolTable.seek(index*Register.reg_long);
            reg.setKey(symbolTable.readLong());
            reg.setToken(readString(25).trim());
            reg.setType(readString(10).trim());
            reg.setLength(symbolTable.readInt());
            reg.setValue(readString(50).trim());
            reg.setCategory(readString(2).trim());
        } catch (IOException e) { e.printStackTrace(); }
        if(reg.getToken().trim().equals(""))
            reg = null; //Data not found
        return reg;
    }

    public String readDataFromTable() {
        String dataTable = "";
        try {
            indexFile.seek(0);
            while (indexFile.getFilePointer() < indexFile.length()) {
                symbolTable.seek(indexFile.readLong()*Register.reg_long);
                dataTable += String.format("%d", symbolTable.readLong());
                dataTable += String.format("%6s", readString(25).trim());
                dataTable += readString(10).trim() + "\t";
                dataTable += symbolTable.readInt() + "\t";
                dataTable += readString(50).trim() + "\t";
                dataTable += readString(2).trim() + "\t \n";
            }
        } catch (IOException e) { e.printStackTrace(); }
        return dataTable;
    }

    /** Retrieve string from a element of a register
     *
     * @param length of the string to be retrieve
     * @return string retrieved from a element of a register
     * @throws IOException
     */
    public String readString(int length) throws IOException {
        char [] result = new char[length];
        for (int i = 0; i < length ; i++){
            result[i] = symbolTable.readChar();
        }
        return String.valueOf(result);
    }

}
