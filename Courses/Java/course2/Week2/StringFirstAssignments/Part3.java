
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb){
        int lengthStringa = stringa.length();
        int firstOccur = stringb.indexOf(stringa);
        if(firstOccur == -1) return false;
        int secondOccur = stringb.indexOf(stringa,firstOccur + lengthStringa);
        if(secondOccur == -1) return false;
        return true;
    }
    
    public String lastPart (String stringa, String stringb) {
        int firstOccur = stringb.indexOf(stringa);
        if(firstOccur == -1) return stringb;
        else {
            return stringb.substring(firstOccur + stringa.length());
        }
    }
    
    public void testing() {
        String stringa = "";
        String stringb = "";
        
        stringa = "a";
        stringb = "bbbbaa";        
        System.out.println("String a: " + stringa + " String b: " + stringb + " test :" + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "c";
        stringb = "bbbba";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test :" + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "abc";
        stringb = "bbaabcaaaedabcaaeee";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "abc";
        stringb = "abcabc";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "abc";
        stringb = "abcaaabc";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "abc";
        stringb = "abccabc";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
    
        stringa = "abc";
        stringb = "abcabcabc";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "an";
        stringb = "banana";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("String a: " + stringa + " String b: " + stringb + " test : " + twoOccurences(stringa,stringb));
        System.out.println("Last part: " + lastPart(stringa,stringb));
        
    }
}
