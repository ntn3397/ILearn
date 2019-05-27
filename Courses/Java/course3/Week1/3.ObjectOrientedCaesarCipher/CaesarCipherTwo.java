
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    CaesarCipherTwo(int key1, int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder result = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            boolean isCap = Character.isUpperCase(ch);
            ch = Character.toLowerCase(ch);
            String shiftedAlphabet;
            if(i%2==0){
                shiftedAlphabet = shiftedAlphabet1;
            } else {
                shiftedAlphabet = shiftedAlphabet2;
            }
            int dex = alphabet.indexOf(ch);
            if(dex!=-1){
                char shiftedChar = shiftedAlphabet.charAt(dex);
                if(isCap){
                    shiftedChar = Character.toUpperCase(shiftedChar);
                }
                result.setCharAt(i,shiftedChar);
            }
        }
        return result.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cct.encrypt(input);
    }
    
}
