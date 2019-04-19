import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public float cgRatio(String dna) {
        int count = 0;
        for(int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') count++;
        }
        return ((float) count)/dna.length();
    }
    
    public void processGenes(StorageResource sr) {
        System.out.println("all the Strings in sr that are longer than 60 characters");
        for (String s : sr.data()){
            if(s.length()>60) System.out.println(s);
        }
        System.out.println("----------");
        System.out.println("number of Strings in sr that are longer than 60 characters");
        int count = 0;
        for (String s :sr.data()){
            if(s.length()>60) count++;
        }
        System.out.println(count);
        System.out.println("----------");
        System.out.println(" Strings in sr whose C-G-ratio is higher than 0.35");
        for(String s: sr.data()){
            if(cgRatio(s) > 0.35) System.out.println(s);
        }
        System.out.println("----------");
        System.out.println("number of strings in sr whose C-G-ratio is higher than 0.35");
        count =0;
        for(String s: sr.data()){
            if(cgRatio(s) > 0.35) count ++;
        }
        System.out.println(count);
        System.out.println("----------");
        System.out.println("length of the longest gene in sr");
        int longestLen = 0;
        for(String s: sr.data()){
            if(longestLen < s.length()) longestLen = s.length();
        }
        System.out.println(longestLen);
    }
    
    //Find Gene
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        int currIndex = stopIndex;
        
        while(currIndex != -1) {
            if((currIndex - startIndex)%3 == 0) return currIndex;
            else {
                currIndex = currIndex + 1;
                currIndex = dna.indexOf(stopCodon, currIndex);
            }
        }
        return dna.length();
        
        
    }
    
    public String findGenes(String dna,int startIndex) {
        if(startIndex == -1 ) return "";

        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex,"TGA");
        
        int stopIndex = Math.min(taaIndex, tagIndex);
        stopIndex = Math.min(stopIndex, tgaIndex);
        
        if(stopIndex == dna.length()) return "";
        
        String gene = dna.substring(startIndex, stopIndex + 3);
        return gene;
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        
        int afterIndex = 0;
        int count = 1;
        while(true){
            int startIndex = dna.indexOf("ATG",afterIndex);
            
            afterIndex += startIndex - afterIndex;
            
            String gene = findGenes(dna,startIndex);
            
            if(gene == "") break;
            
            sr.add(gene);
            
            afterIndex +=  gene.length();
        }
        
        return sr;
    }
    
    //End Find Genes
    
    
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
    
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        String dna;
        // longer than 9 characters
        dna ="xxxyyyxxxyyy";
        sr.add(dna);
        //no genes longer than 9 characters,
        dna ="xxx";
        sr.add(dna);
        //genes whose C-G-ratio is higher than 0.35,
        dna = "CGCGCGxxx";
        sr.add(dna);
        // some genes whose C-G-ratio is lower than 0.35
        dna = "CGxxxyyyxxxyyyxxx";
        sr.add(dna);
        processGenes(sr);
        System.out.println("------------Test with File-----------------");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna1 = fr.asString();
        dna1 = dna1.toUpperCase();
        StorageResource sr1 = new StorageResource();
        sr1 = getAllGenes(dna1);
        processGenes(sr1);
        System.out.println("size " + sr1.size());
        System.out.println("count CTG: " + countCTG(dna1));
    }
}
