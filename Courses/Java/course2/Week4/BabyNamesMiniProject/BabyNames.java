import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.List;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void totalBirths(FileResource fr){
        CSVParser parser = fr.getCSVParser(false);
        int total = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boyCount = 0;
        int girlCount = 0;
        for(CSVRecord record : parser){
            total+= Integer.parseInt(record.get(2));
            String sex = record.get(1);
            if(sex.equals("F")){
                girlCount++;
                totalGirls+= Integer.parseInt(record.get(2));
            }
            if(sex.equals("M")) {
                boyCount++;
                totalBoys += Integer.parseInt(record.get(2));
            }
        }
        System.out.println("total: " + total);
        System.out.println("total boys: " + totalBoys);
        System.out.println("total girls: " + totalGirls);
        System.out.println("boy count: " + boyCount);
        System.out.println("girl count: " + girlCount);
        
    }
    
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int countGirl(CSVParser parser){
        int count =0;
        for(CSVRecord record : parser){
            if(record.get(1).equals("F")) count++;
        }
        return count;
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        //FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                rank++;
                if(record.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank(){
        System.out.println(getRank(1971,"Frank","M"));
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        //FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        for(CSVRecord record : parser){
            if(gender.equals("F")){
                count++;
                if(count == rank) return record.get(0);
            } else {
                if(gender.equals("M") && record.get(1).equals("M")){
                    count++;
                    if(count == rank) return record.get(0);
                }
            }
            
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        System.out.println(getName(1982,450,"M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        FileResource frNewYear = new FileResource("us_babynames/us_babynames_by_year/yob"+newYear+".csv");
        //FileResource frYear = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        //FileResource frNewYear = new FileResource("us_babynames/us_babynames_test/yob"+newYear+"short.csv");
        int rankYear = getRank(year, name, gender);
        String nameNewYear = getName(newYear, rankYear, gender);
        System.out.println(name + " born in " + year + " would be " + nameNewYear + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen",1974,2014,"M");
    }
    
    public String yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 999999999;
        String year = "";
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int currentRank = 0;
            for(CSVRecord record : parser) {
                if(record.get(1).equals(gender)){
                    currentRank++;
                    if(record.get(0).equals(name)){
                        if(highestRank > currentRank) {
                            highestRank = currentRank;
                            year = f.getName();
                        }
                    }
                }
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int count = 0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int currentRank = 0;
            for(CSVRecord record : parser) {
                if(record.get(1).equals(gender)){
                    currentRank++;
                    if(record.get(0).equals(name)){
                        sum+=currentRank;
                        count++;
                    }
                }
            }
        }
        return ((double)sum)/count;
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Robert","M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        //FileResource fr = new FileResource("us_babynames/us_babynames_test/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int rank = getRank(year,name,gender);
        int count = 0;
        int total = 0;
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                count ++;
                if(count < rank) {
                    total += Integer.parseInt(record.get(2));
                }
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    
}
