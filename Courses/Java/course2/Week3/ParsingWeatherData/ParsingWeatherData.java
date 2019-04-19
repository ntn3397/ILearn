import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingWeatherData {
    public CSVRecord biggerRecord(CSVRecord currentRecord, CSVRecord biggerSoFar, String header){
        if(biggerSoFar == null) {
            biggerSoFar = currentRecord;
        } else {
            double current = Double.parseDouble(currentRecord.get(header));
            double bigger = Double.parseDouble(biggerSoFar.get(header));
            if(bigger < current) {
                biggerSoFar = currentRecord;
            }
        }
        return biggerSoFar;
    }
    
     public CSVRecord smallerRecord(CSVRecord currentRecord, CSVRecord smallerSoFar, String header){
        if(smallerSoFar == null) {
            smallerSoFar = currentRecord;
        } else {
            double currentTemp = Double.parseDouble(currentRecord.get(header));
            double smallerTemp = Double.parseDouble(smallerSoFar.get(header));
            if(smallerTemp > currentTemp) {
                smallerSoFar = currentRecord;
            }
        }
        return smallerSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        
        for ( CSVRecord currentRecord : parser){
            if(coldestSoFar == null) {
                if(currentRecord.get("TemperatureF").equals("-9999")) continue;
                coldestSoFar = currentRecord;
                
            } else {
                if(currentRecord.get("TemperatureF").equals("-9999")) continue;
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(coldestTemp > currentTemp) {
                    coldestSoFar = currentRecord;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestSoFar = coldestHourInFile(parser);
        System.out.println(coldestSoFar.get("TemperatureF")+" " + coldestSoFar.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        File coldestSoFarFile = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourInFile(parser);
            if(coldestSoFar == null) {
                if(currentRecord.get("TemperatureF").equals("-9999")) continue;
                coldestSoFar = currentRecord;
            } else {
                if(currentRecord.get("TemperatureF").equals("-9999")) continue;
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(coldestTemp > currentTemp) {
                    coldestSoFar = currentRecord;
                    coldestSoFarFile = f;
                }
            }
        }
        return coldestSoFarFile.getName();
    }
    
    public void testFileWithColdestTemperature(){
        
        System.out.println("Coldest day was in file " + fileWithColdestTemperature());
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were");
        parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.println(record.get("DateUTC")+" " + record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRecord : parser){
            if(lowestSoFar == null) {
                if(currentRecord.get("Humidity").equals("N/A")) continue;
                lowestSoFar = currentRecord;
            } else {
                if(currentRecord.get("Humidity").equals("N/A")) continue;
                int currentHum = Integer.parseInt(currentRecord.get("Humidity"));
                int lowestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
                if(lowestHum > currentHum) {
                    lowestSoFar = currentRecord;
                }                
            }
        }
        return lowestSoFar;
        
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            lowestSoFar = smallerRecord(currentRecord, lowestSoFar,"Humidity");
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestRecord.get("Humidity") + " " + lowestRecord.get("DateUTC"));
    }
    
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;
        for(CSVRecord record : parser){
            sum+= Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        if(count == 0) return 0;
        return ((double)sum)/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count =0;
        for(CSVRecord record : parser) {
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity>= value) {
                sum+= Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if(count==0) return 0;
        return ((double)sum)/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average =averageTemperatureWithHighHumidityInFile(parser,80);
        System.out.println("Average Temp when high Humidity is " + average);
    }
}
