import edu.duke.*;
/**
 * Write a description of CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CommonWords {
    public String[] getCommon(){
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String word : fr.words()){
            common[index] = word;
            index++;
        }
        return common;
    }
    
    public int indexOf(String[] list, String word) {
        int index = 0;
        for(String common : list){
            if(common.equals(word)){
                return index;
            }
            index++;
        }
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts){
        for(String word : resource.words()){
            int index = indexOf(common,word);
            if(index!=-1){
                counts[index]++;
            }
        }
    }
    
    void countShakespeare(){
        String[] listFile = {"caesar.txt","common.txt","errors.txt",
            "hamlet.txt","likeit.txt","macbeth.txt","romeo.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        //String[] listFile = {"small.txt"};
        for(int k = 0; k < listFile.length; k++){
            FileResource resource = new FileResource("data/" + listFile[k]);
            countWords(resource,common,counts);
            System.out.println("done with " + listFile[k]);
        }
        
        for(int k = 0; k < counts.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
