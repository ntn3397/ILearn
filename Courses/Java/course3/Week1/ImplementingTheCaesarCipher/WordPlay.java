
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if(ch=='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        sb.append(phrase);
        for(int i = 0; i < sb.length(); i++){
            if(isVowel(sb.charAt(i))){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels(){
        String phrase = "HEllo World";
        System.out.println(replaceVowels(phrase,'*'));
    }
    
    public String emphasize (String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        sb.append(phrase);
        for(int i = 0; i <sb.length(); i++){
            if(isVowel(sb.charAt(i)) && sb.charAt(i) == ch){
                if(i%2==0){
                    sb.setCharAt(i,'*');
                } else {
                    sb.setCharAt(i,'+');
                }
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize(){
        String phrase = "dna ctgaaactga";
        System.out.println(emphasize(phrase,'a'));
        phrase = "Mary Bella Abracadabra";
        System.out.println(emphasize(phrase,'a'));
    }
}
