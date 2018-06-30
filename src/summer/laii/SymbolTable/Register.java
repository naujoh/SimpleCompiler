package summer.laii.SymbolTable;

public class Register {
    private long key; // 8 bytes
    private String token; //char[25] = 50 bytes
    private String type; //char[10] = 20 bytes
    private int length; // 4 bytes
    private String value; //char[50] = 100 bytes
    private String category; //char[2] = 4 bytes

    public static int reg_long = Long.BYTES + 89*Character.BYTES + Integer.BYTES;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = adjustCharacterSize(token, 25);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = adjustCharacterSize(type, 10);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int lenght) {
        this.length = lenght;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = adjustCharacterSize(value, 50);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = adjustCharacterSize(category, 2);
    }

    private String adjustCharacterSize(String value, int size) {
        StringBuffer stringBuffer = new StringBuffer(value);
        stringBuffer.setLength(size);
        return stringBuffer.toString();
    }
}
