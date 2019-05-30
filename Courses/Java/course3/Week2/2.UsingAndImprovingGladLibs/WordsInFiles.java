import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myMap;
    WordsInFiles(){
        myMap = new HashMap<String, ArrayList<String>>();
        
    }
    
    private void addWordsFromFile(File f){
        FileResource resource = new FileResource(f);
        for(String w : resource.words()){
            if(myMap.keySet().contains(w)){
                if(!myMap.get(w).contains(f.getName())){
                    myMap.get(w).add(f.getName());
                }      
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                myMap.put(w,list);
            }
        }
    }
    
    public void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String k : myMap.keySet()){
            if(max < myMap.get(k).size()){
                max = myMap.get(k).size();
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for(String k : myMap.keySet()){
            if(myMap.get(k).size() == number){
                wordList.add(k);
            }
        }
        return wordList;
    }
    
    public void printFilesIn(String word){
        for(String s : myMap.get(word)){
            System.out.println(s);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Words in all file: " + myMap.size());
        ArrayList<String> wordsInMaxNumFiles = wordsInNumFiles(max);
        //for(String w : wordsInMaxNumFiles){
        //    System.out.println(w+" in files: ");
        //    printFilesIn(w);
        //    System.out.println("---");
        //}
        ArrayList<String> wordsIn4OfFiles = wordsInNumFiles(4);
        System.out.println("Number of words appear four: " + wordsIn4OfFiles.size());
        //for(String w : myMap.keySet()){
        //    System.out.print(w+" :");
        //    for(String s : myMap.get(w)){
        //        System.out.print(s+" ");
        //    }
        //    System.out.println();
        //}
        
        System.out.println("the word 'sad' appear in: "+myMap.get("sad"));
        System.out.println("the word 'red' appear in: "+myMap.get("red"));
    }
}
