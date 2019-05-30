import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> myMap;
    CodonCount(){
        myMap = new HashMap<String,Integer>();
        
    }
    
    public int buildCodonMap(int start, String dna){
        myMap.clear();
        int len = dna.length();
        //not count the stuff left of dna
        len = len - len%3;
        if(len <3) return -1;
        for(int i = start; i < len; i+=3){
            //start increase by i so len must decrease
            if(len - i < 3) return -1;
            String codon = dna.substring(i,i+3);
            if(myMap.keySet().contains(codon)){
                myMap.put(codon,myMap.get(codon)+1);
            } else {
                myMap.put(codon, 1);
            }     
        }
        return 1;
    }
    public String getMostCommonCodon(){
        String maxCodon = "";
        int max = 0;
        for(String s: myMap.keySet()){
            if(max < myMap.get(s)){
                max = myMap.get(s);
                maxCodon = s;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : myMap.keySet()){
            if(myMap.get(s) >=start && myMap.get(s) <=end){
                System.out.println(s+"\t"+myMap.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource resource = new FileResource();
        String dna = resource.asString();
        dna = dna.trim();
        dna = dna.toUpperCase();
        
        for(int i = 0; i < 3; i++){
            buildCodonMap(i,dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Reading frame starting with "+i+" results in " +
                myMap.size() + " unique codons");
            System.out.println("and most common codon is " + mostCommonCodon +
                " with count " + myMap.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1,5);
        }
    }
}
