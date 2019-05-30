import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> freqs;
    
    CharactersInPlay(){
        characters = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    public void update(String person){
        
        int dex = characters.indexOf(person);
        if(dex==-1){
            characters.add(person);
            freqs.add(1);
        } else {
            int value = freqs.get(dex);
            freqs.set(dex, value+1);
        }
    }
    
    public void findAllCharacters(){
        characters.clear();
        freqs.clear();
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            int periodDex = line.indexOf(".");
            if(periodDex!=-1){
                String character = line.substring(0,periodDex);
                update(character);
            }
        }
    }
    
    public int findMostSpeak(){
        int max = freqs.get(0);
        int dex = 0;
        for(int i = 1; i < freqs.size(); i++){
            if(max < freqs.get(i)){
                max = freqs.get(i);
                dex = i;
            }
        }
        return dex;
    }
    
    public int charactersWithNumParts(int num1, int num2){
        if(num1 > num2){
            return -1;
        }
        System.out.println("Character speak > "+ num1 + " and < "+ num2);
        for(int i = 0; i < freqs.size(); i++){
            if(freqs.get(i) >= num1 && freqs.get(i) <= num2){
                System.out.println(characters.get(i)+"\t"+freqs.get(i));
            }
        }
        return 1;
    } 
    
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < characters.size(); i++){
            if(freqs.get(i)>1){
                System.out.println(characters.get(i)+"\t"+freqs.get(i));
            }
        }
        int maxDex = findMostSpeak();
        System.out.println("Most Speak: "+characters.get(maxDex)+"\t"+freqs.get(maxDex));
        charactersWithNumParts(10, 15);
    }
    
}
