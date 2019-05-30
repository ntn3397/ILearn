import edu.duke.*;
import java.util.*;
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedWord;
    private ArrayList<String> usedCategory;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWord = new ArrayList<String>();
        usedCategory = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWord = new ArrayList<String>();
        usedCategory = new ArrayList<String>();
    }
    private void getSourceMap(String source, HashMap<String,ArrayList<String>> sourceMap){
        FileResource resource = new FileResource(source+"/sourcemap.txt");
        for(String line : resource.lines()){
            int colonDex = line.indexOf(":");
            String key = line.substring(0,colonDex);
            String value = line.substring(colonDex+1);
            key = key.trim();
            value = value.trim();
            if(sourceMap.keySet().contains(key)){
                sourceMap.get(key).add(value);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(value);
                sourceMap.put(key,list);
            }
        }
    }
    private void initializeFromSource(String source) {
        HashMap<String,ArrayList<String>> sourceMap = new HashMap<String,ArrayList<String>>();
        getSourceMap(source,sourceMap);
        //sourceMap label -> ArrayList sources
        //source have words.
        
        for(String label : sourceMap.keySet()){
            //this "list" get all word form sources of label
            ArrayList<String> list = new ArrayList<String>();
            for(String src : sourceMap.get(label)){
                ArrayList<String> temp = readIt(src);
                list.addAll(temp);
            }
            myMap.put(label,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(!label.equals("number")){
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        
        String category = w.substring(first+1,last);
        if(!usedCategory.contains(category) && !category.equals("number")){
            usedCategory.add(category);
        }
        
        String sub = getSubstitute(category);
        int dex = usedWord.indexOf(sub);
        while(dex!=-1){
            sub = getSubstitute(w.substring(first+1,last));
            dex = usedWord.indexOf(sub);
        }
        usedWord.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int sum = 0;
        for(String label : myMap.keySet()){
            sum+=myMap.get(label).size();
        }
        return sum;
    }
    
    private int totalWordsConsidered(){
        int sum = 0;
        if(usedCategory.size()==0) return 0;
        for(String label : usedCategory){
            sum+= myMap.get(label).size();
        }
        return sum;
    }
    
    public void makeStory( ){
        usedWord.clear();
        usedCategory.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 1000);
        System.out.println();
        System.out.println("Total word replace: " + usedWord.size());
        System.out.println("Used word: ");
        for(int i = 0; i < usedWord.size(); i++){
            System.out.print(usedWord.get(i) + " ");
        }
        System.out.println();
        System.out.println("Total word in map: " + totalWordsInMap());
        System.out.println("Total word cosidered: " + totalWordsConsidered());
        
    }
}
