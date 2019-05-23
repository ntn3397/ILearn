import edu.duke.*;
/**
 * Write a description of Decryption2KeyCaesarCypher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Decryption2KeyCaesarCypher {
    public static void countLetters(String message, int[] counts){
        //             xi
        //             ge
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1) {
                counts[dex]++;
            }
        }
    }
    
    public static int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k <vals.length; k++){
            if(vals[k] >vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    public static String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = new int[26];
        countLetters(encrypted,freqs);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
        
    }
    
    public static String halfOfString(String message, int start){
        String result = "";
        for(int i = start; i < message.length(); i+=2){
            result+= message.charAt(i);
        }
        return result;
    }
    
    public static int getKey(String s){
        int[] freqs = new int[26];
        countLetters(s,freqs);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - ( 4 - maxDex);
        }
        return dkey;
    }
    
    public static String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String half0 = halfOfString(encrypted,0);
        String half1 = halfOfString(encrypted,1);
        int dKeyHalf0 = getKey(half0);
        int dKeyHalf1 = getKey(half1);
        String dHalf0 = cc.encrypt(half0, 26 - dKeyHalf0);
        String dHalf1 = cc.encrypt(half1, 26 - dKeyHalf1);
        String result = "";
        for(int i = 0; i < dHalf0.length(); i++){
            result+= Character.toString(dHalf0.charAt(i));
            if(i < dHalf1.length()){
                result+= Character.toString(dHalf1.charAt(i));
            }
        }
        return result;
    }
    
    public static void testDecryptTwoKeys(){
        FileResource resource = new FileResource();
        String encrypted = resource.asString();
        System.out.println(decryptTwoKeys(encrypted));
    }
}
