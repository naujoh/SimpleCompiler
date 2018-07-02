package summer.laii.SymbolTable;

public class HashTable {
    private int size = 1123; //Size of hash table (prime number recommended)

    /** A bitwise hash function written by Justin Sobel.
     *
     * @param value string to be hashed
     * @return hashed value of the string
     */
    public long getHash(String value) {
        long hash = 1315423911;
        for(int i = 0; i < value.length(); i++) { hash ^= ((hash << 5) + value.charAt(i)*(i+1)+ (hash >> 2)); }
        hash = hash & 0x7FFFFFFF;
        return hash%size;
    }

    //TODO Solve problem to insert and find data from symbol table when a collision occurs
}