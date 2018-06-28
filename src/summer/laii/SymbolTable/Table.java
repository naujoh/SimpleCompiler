package summer.laii.SymbolTable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Table {
    private RandomAccessFile symbolTable;
    private String filepath = "symbol_table";
    private HashTable hashTable;

    public Table() {
        try {
            symbolTable = new RandomAccessFile(filepath, "rw");
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        hashTable = new HashTable();
    }

    /** Insert a register to symbol table
     *
     * @param register Object to insert in symbol table
     */
    public void insertData(Register register) {
        long index = hashTable.getHash(register.getToken());
        try {
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
