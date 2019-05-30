import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String word : resource.words()){
            word = word.toLowerCase();
            int dex = myWords.indexOf(word);
            if(dex==-1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(dex);
                myFreqs.set(dex, value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("Unique word: "+myWords.size());
        //for(int i = 0; i < myWords.size(); i++){
        //    System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
        //}
        int maxDex = findIndexOfMax();
        System.out.println("Word most freq: "+myWords.get(maxDex) + ":"+myFreqs.get(maxDex));
    }
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int dex = 0;
        for(int i = 1; i < myFreqs.size(); i++){
            if(max < myFreqs.get(i)){
                max = myFreqs.get(i);
                dex = i;
            }
        }
        return dex;
    }
}
