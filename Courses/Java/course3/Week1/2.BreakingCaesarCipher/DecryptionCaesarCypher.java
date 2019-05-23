
/**
 * Write a description of DecryptionCaesarCypher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DecryptionCaesarCypher {
    public static void countLetters(String message, int[] counts){
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
            if(vals[k] >vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    public String decrypt(String encrypted){
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
    
}
