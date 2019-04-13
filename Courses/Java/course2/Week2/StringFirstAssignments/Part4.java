import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findWebLinks() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        for (String s : ur.words()){
            String lowerS = s.toLowerCase();
            int indexYoutube = lowerS.indexOf("youtube.com");
            if(indexYoutube == -1) continue;
            int leftQuote = lowerS.indexOf("\"");
            int rightQuote = lowerS.lastIndexOf("\"");
            String result = s.substring(leftQuote+1, rightQuote);
            System.out.println(result);
        }
    }
    
    public void testing() {
        findWebLinks();
    }
    
}
