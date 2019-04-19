
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    public int countGenes(String dna) {
        int afterIndex = 0;
        int count = 0;
        while(true){
            int startIndex = dna.indexOf("ATG",afterIndex);
            
            afterIndex += startIndex - afterIndex;
            
            String gene = findGenes(dna,startIndex);
            
            if(gene == "") break;
            
            count ++;
            
            afterIndex +=  gene.length();
        }
        
        return count;
    }
    
    public void testCountGenes(){
        String dna;
        dna="xxxyyyATGxxxyyyxxxyyTAAyTAATAGATGTAGTAAxxxTAGATGxxxyyyxTAAxTAGxTGA";
        System.out.println("dna = " + dna);
        printAllGenes(dna);
        System.out.println(countGenes(dna));
        System.out.println("--------------");
        
        dna="ATGxxxTAA";
        System.out.println("dna = " + dna);
        printAllGenes(dna);
        System.out.println(countGenes(dna));
        System.out.println("--------------");
        
        dna="xxx";
        System.out.println("dna = " + dna);
        printAllGenes(dna);
        System.out.println(countGenes(dna));
        System.out.println("--------------");
        
        dna="";
        System.out.println("dna = " + dna);
        printAllGenes(dna);
        System.out.println(countGenes(dna));
        System.out.println("--------------");
        
        dna="ATGxxxTAAxxxATGxxxTAA";
        System.out.println("dna = " + dna);
        printAllGenes(dna);
        System.out.println(countGenes(dna));
        System.out.println("--------------");
        
    }
}
