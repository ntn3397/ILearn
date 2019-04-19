import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingExportData {
    public void countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String countryRecord = record.get("Country");
            if(countryRecord.equals(country)) {
                System.out.print(record.get("Country") + ": ");
                System.out.print(record.get("Exports") + ": ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1)&&exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.print(record.get("Country")+" ");
                System.out.println(value);
            }
        }
    }
    
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String find ="Nauru";
        System.out.println("1.");
        countryInfo(parser, find);
        parser = fr.getCSVParser();
        System.out.println("2. list Exporter two products");
        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        System.out.println("3. NumberOfExporter");
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        System.out.println("4. big exporter");
        bigExporters(parser,"$999,999,999,999");
    }
}
