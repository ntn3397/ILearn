import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
        //            v
        String dna = "xxxATGyyyxxxyyyxxTAA";
        System.out.println("Test 1 : " + findStopCodon(dna,3,"TAA"));
        
        dna = "xxxATGyyyxxxyyyxxxTAA";
        System.out.println("Test 2 : " + findStopCodon(dna,3,"TAA"));
        
        dna = "xxxATGTAA";
        System.out.println("Test 3 : " + findStopCodon(dna,3,"TAA"));
        
        dna = "xxxATGxxxyTAAyTAAyTAAxxxyyy";
        System.out.println("Test 3 : " + findStopCodon(dna,3,"TAA"));
        
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
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
    
    public void testFindGene(){
        String dna;
        //No ATG
        dna = "xxxyyyxxxyyyxxxyyyTAA";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        //one valid stop codon
        dna = "xxxATGxxxyyyxxxyyTAAxxxyyy";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        // have multiple valid
        dna = "xxxATGxxxyyyxxxyyyTAGxxxyyy";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        //no valid stop codon
        dna = "xxxATGxxxyyyxxxyyyxxxyyy";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        
        dna = "xxxATG";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        
        dna = "xxxxxxx";
        System.out.println("dna strand: " + dna);
        System.out.println("gene finded: " + findGene(dna));
        System.out.println("---------------");
        
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
    
    public void printAllGenes(String dna) {
        int afterIndex = 0;
        int count = 1;
        while(true){
            int startIndex = dna.indexOf("ATG",afterIndex);
            
            afterIndex += startIndex - afterIndex;
            
            String gene = findGenes(dna,startIndex);
            
            if(gene == "") break;
            
            System.out.println(count + ". " + gene);
            count ++;
            
            afterIndex +=  gene.length();
        }
        
    }
    
    public void testPrintAllGenes(){
        String dna = "xxxATGxxxyyyxxxyyTAAyTAAxxxyyATGxxxyyyxxxyyyTAAxxxyyyTAAxxxTAGxxxATGxxxyyyTAGxxxTAGxxxTAGTAGATGTAA";
        printAllGenes(dna);
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
    
    public void testStorageResource(){
        String dna = "xxxATGxxxyyyxxxyyTAAyTAAxxxyyATGxxxyyyxxxyyyTAAxxxyyyTAAxxxTAGxxxATGxxxyyyTAGxxxTAGxxxTAGTAGATGTAA";
        StorageResource listGenes = getAllGenes(dna);
        int count = 1;
        for(String s : listGenes.data()){
            System.out.println(count + ". " + s);
            count++;
        }
    }
}
