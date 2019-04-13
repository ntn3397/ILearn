
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna) {
        int indexStart = dna.indexOf("ATG");
        if(indexStart == -1) return "";
        int indexEnd = dna.indexOf("TAA",indexStart + 3 );
        if(indexEnd == -1) return "";
        if((indexEnd - indexStart)%3 != 0) return "";
        return dna.substring(indexStart, indexEnd +3);
    }
    
    public void testSimpleGene () {
        String dna = "AAAGTGGTTAA"; //no ATG
        System.out.println(findSimpleGene(dna));
        dna = "AAATGGTTGGTATTGTATGGGAATTTTGG"; //no TAA
        System.out.println(findSimpleGene(dna));
        dna = "AAAAGGTTAATGGTATGTTAA"; // multiple of 3
        System.out.println(findSimpleGene(dna));
        dna = "AAATTGGATGTTTTTAAGGTTAA"; // not multiple of 3
        System.out.print(findSimpleGene(dna));
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println(findSimpleGene(dna));
        
    }
    
}
