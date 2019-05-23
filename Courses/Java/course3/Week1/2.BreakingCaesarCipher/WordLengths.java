import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public static void countWordLengths(FileResource resource, int[] counts){
        int largestSizeCount = counts.length - 1;
        for(String word : resource.words()){
            int len = word.length();
            if(!Character.isLetter(word.charAt(0))){
                len--;
            }
            if(!Character.isLetter(word.charAt(word.length()-1))){
                len--;
            }
            if(len > largestSizeCount){
                counts[largestSizeCount]++;
            } else if(len > 0){
                counts[len]++;
            }
        }
    }
    
    public static void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        for(int k = 0; k < 31; k++){
            if(counts[k]!=0){
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        System.out.println("Most common word length is " + indexOfMax(counts));
    }
    
    public static int indexOfMax(int[] values){
        int maxDex = 0;
        for(int k = 1; k < values.length; k++){
            if(values[maxDex] < values[k]){
                maxDex = k;
            }
        }
        return maxDex;
    }
}
