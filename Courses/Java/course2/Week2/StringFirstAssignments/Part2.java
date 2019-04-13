
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        int indexStart = dna.indexOf(startCodon);
        if(indexStart == -1) return "";
        int indexStop = dna.indexOf(stopCodon,indexStart + 3 );
        if(indexStop == -1) return "";
        if((indexStop - indexStart)%3 != 0) return "";
        return dna.substring(indexStart, indexStop +3);
    }
    
    public void testSimpleGene () {
        String dna = "AAAGTGGTTAA"; //no ATG
        System.out.print(findSimpleGene(dna,"ATG","TAA"));
        dna = "AAATGGTTGGTATTGTATGGGAATTTTGG"; //no TAA
        System.out.print(findSimpleGene(dna, "ATG", "TAA"));
        dna = "AAAAGGTTAATGGTATGTTAA"; // multiple of 3
        System.out.print(findSimpleGene(dna, "ATG", "TAA"));
        dna = "AAATTGGATGTTTTTAAGGTTAA"; // not multiple of 3
        System.out.print(findSimpleGene(dna, "ATG", "TAA"));
    }
}
