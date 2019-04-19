
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int currIndex = 0;
        int startIndex = 0;
        int count = 0;
        while(true) {
            currIndex = stringb.indexOf(stringa,currIndex);
            if(currIndex == -1) break;
            currIndex += stringa.length();
            count++;
        }
        return count;
    }
    
    public void testHowMany(){
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
}
