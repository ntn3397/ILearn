import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public void countLetters(String message, int[] counts){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1) {
                counts[dex]++;
            }
        }
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k <vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public void simpleTests(){
        FileResource resource = new FileResource();
        String input = resource.asString();
        CaesarCipher cc18 = new CaesarCipher(18);
        String encryptedInput = cc18.encrypt(input);
        System.out.println(encryptedInput);
        System.out.println(cc18.decrypt(encryptedInput));
        System.out.println("breakCaesarCipher: " + breakCaesarCipher(input));
        
    }
    
    public String breakCaesarCipher(String input){
        int[] counts = new int[26];
        countLetters(input,counts);
        int maxDex = maxIndex(counts);
        int key = maxDex - 4;
        if(maxDex < 4){
            key = 26-(4-maxDex);
        }
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
}
