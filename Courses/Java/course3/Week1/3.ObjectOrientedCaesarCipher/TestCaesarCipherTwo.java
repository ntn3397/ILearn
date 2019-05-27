import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start){
        StringBuilder result = new StringBuilder();
        for(int i = start; i < message.length(); i+=2){
            result.append(message.charAt(i));
        }
        return result.toString();
    }
    
    private void countLetters(String message, int[] counts){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1) {
                counts[dex]++;
            }
        }
    }
    
    private int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k <vals.length; k++){
            if(vals[k] >vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    private int getKey(String s){
        int[] freqs = new int[26];
        countLetters(s,freqs);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - ( 4 - maxDex);
        }
        return dkey;
    }
    
    public void simpletests(){
        FileResource resource = new FileResource();
        String input = resource.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        System.out.println("Input: " + input);
        String eInput = cct.encrypt(input);
        System.out.println("Encrypted: " + eInput);
        String dInput = cct.decrypt(eInput);
        System.out.println("Decrypted: " + dInput);
        String breakCCResult = breakCaesarCipher(input);
        System.out.println("Break CeasarCypher: " + breakCCResult);
    }
    
    public String breakCaesarCipher(String input){
        
        String half0 = halfOfString(input,0);
        String half1 = halfOfString(input,1);
        int keyHalf0 = getKey(half0);
        int keyHalf1 = getKey(half1);
        CaesarCipher cc0 = new CaesarCipher(keyHalf0);
        CaesarCipher cc1 = new CaesarCipher(keyHalf1);
        String dHalf0 = cc0.decrypt(half0);
        String dHalf1 = cc1.decrypt(half1);
        StringBuilder result = new StringBuilder();
        for(int i =0; i < dHalf0.length(); i++){
            result.append(Character.toString(dHalf0.charAt(i)));
            if(i < dHalf1.length()){
                result.append(Character.toString(dHalf1.charAt(i)));
            }
        }
        return result.toString();
    }
}
