
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public float cgRatio(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') count++;
        }
        return (float) count/dna.length();
    }
    
    public void testcgRatio(){
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dna) {
        int startIndex =0;
        int currIndex = 0;
        int count = 0;
        while(true) {
            startIndex = dna.indexOf("CTG",currIndex);
            if(startIndex==-1) break;
            currIndex = startIndex + 3;
            count ++;
        }
        return count;
    }
    
    public void testCountCTG() {
        String dna = "CTGxxxyyyCTGxxxyyxxyyxyxyxyxyxyCTGCTGxxxyyy";
        System.out.println(countCTG(dna));
    }
}
