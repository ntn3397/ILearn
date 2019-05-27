
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String capAlphabet;
    private String shiftedAlphabet;
    private String shiftedCapAlphabet;
    private int mainKey;
    CaesarCipher(int key){
        alphabet ="abcdefghijklmnopqrstuvwxyz";
        capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        shiftedCapAlphabet = capAlphabet.substring(key) + capAlphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder result = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(Character.isLowerCase(ch)){
                int dex = alphabet.indexOf(ch);
                if(dex!=-1){
                    result.setCharAt(i,shiftedAlphabet.charAt(dex));
                }
            } else if(Character.isUpperCase(ch)){
                int dex = capAlphabet.indexOf(ch);
                if(dex!=-1){
                    result.setCharAt(i,shiftedCapAlphabet.charAt(dex));
                }
            }
        }
        return result.toString();
    }
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
