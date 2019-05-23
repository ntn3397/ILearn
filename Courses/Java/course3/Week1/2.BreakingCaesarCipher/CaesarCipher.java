import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt (String input, int key){
        String alphabetup = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetlow = "abcdefghijklmnopqrstuvwxyz";
        String cipherup = alphabetup.substring(key) + alphabetup.substring(0,key);
        String cipherlow = alphabetlow.substring(key) + alphabetlow.substring(0,key);
        StringBuilder code = new StringBuilder();
        code.append(input);
        for(int i = 0; i < code.length(); i++) {
            if(Character.isUpperCase(input.charAt(i))){
                int idx = alphabetup.indexOf(input.charAt(i));
                if(idx!=-1){
                    code.setCharAt(i,cipherup.charAt(idx));
                }
            } else {
                int idx = alphabetlow.indexOf(input.charAt(i));
                if(idx!= -1) {
                    code.setCharAt(i, cipherlow.charAt(idx));
                }
            }
        }
        return code.toString();
    }
    public void testCaesar(){
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message,key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String code1 = encrypt(input,key1);
        String code2 = encrypt(input,key2);
        StringBuilder code2key = new StringBuilder();
        code2key.append(code1);
        for(int i = 0; i < input.length(); i++) {
            if(i%2!=0){
                code2key.setCharAt(i,code2.charAt(i));
            }
        }
        return code2key.toString();
    }
    
    public void testEncryptTwoKeys(){
        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(encryptTwoKeys(phrase, 8, 21));
    }
}
