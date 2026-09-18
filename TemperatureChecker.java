/*
Name: Isini Ayansa Perera
Student ID: 23601321
Purpose: contains modules and methods related to temperature checking functionalities and validates the inputs of the user
*/

import java.io.*;
import java.util.Scanner;

public class TemperatureChecker{
    //PERTH
    private static final double PERTH_MORNING= 18.2;
    private static final double PERTH_AFTERNOON= 23.0;
    private static final double PERTH_MIN= 0.7;
    private static final double PERTH_MAX= 46.0;
    //BRISBANE
    private static final double BRISBANE_MORNING= 21.8;
    private static final double BRISBANE_AFTERNOON= 24.8;
    private static final double BRISBANE_MIN= 2.6;
    private static final double BRISBANE_MAX= 41.7;
    //HOBRT
    private static final double HOBART_MORNING= 12.1;
    private static final double HOBART_AFTERNOON= 15.1;
    private static final double HOBART_MIN= -2.8;
    private static final double HOBART_MAX= 41.8;

    public boolean isValidCity(String city){
        String cityLow= city.toLowerCase();
        return cityLow.equals("perth" )|| cityLow.equals("brisbane") || cityLow.equals("hobart");
    }

    public boolean isValidTemp(String city, double temp){
        if(!isValidCity(city)){
            return false;
        }
        String cityLow= city.toLowerCase();

        if(cityLow.equals("perth")){
            return temp >= PERTH_MIN && temp <= PERTH_MAX;
        }
        if(cityLow.equals("brisbane")){
            return temp >= BRISBANE_MIN && temp <= BRISBANE_MAX;
        }
        if(cityLow.equals("hobart")){
            return temp >= HOBART_MIN && temp <= HOBART_MAX;
        }
        return false;
    }

    private double findAvgTemp(String city, String time){
        String cityLow= city.toLowerCase();
        String timeLow= time.toLowerCase();

        //PERTH
        if(cityLow.equals("perth")){
            if(timeLow.equals("morning")){
                return PERTH_MORNING;
            }return PERTH_AFTERNOON;
        }
        //BRISBANE
        if(cityLow.equals("brisbane")){
            if(timeLow.equals("morning")){
                return BRISBANE_MORNING;
            }return BRISBANE_AFTERNOON;
        }
        //HOBART
        if(cityLow.equals("hobart")){
            if(timeLow.equals("morning")){
                return HOBART_MORNING;
            }return HOBART_AFTERNOON;
        }
        return 0.0;
    }

    public String compareAvgTemp(String city, double temp, String time){
        if(!isValidCity(city)){
            throw new IllegalArgumentException("City is unavailable");
        }
        if(!isValidTemp(city, temp)){
            throw new IllegalArgumentException("Not a valid temperature range.");  
        }

        String timeLow= time.toLowerCase();
        if(!timeLow.equals("morning") && !timeLow.equals("afternoon")){
            throw new IllegalArgumentException("Not a valid time of day");
        }

        double avg= findAvgTemp(city, time);
        double diff= temp-avg;
        double absDiff= Math.abs(diff);

        // String result="";
        // result +="City is " +city +"\n";
        // result += "Average temperature is " +avg+ "C\n";

        if(diff>0){
            if(diff>6){
                return "Temperature is above average by " +String.format("%.1f",diff) +" degrees C and difference is greater than 6 degrees C";
            }return "Temperature is above average by " +String.format("%.1f",diff) + " degrees C";
        }
        if(diff<0){
            if(absDiff>6){
                return "Temperature is below average by " +String.format("%.1f",absDiff) + " degrees C and difference is greater than 6 degrees C";
            }return "Temperature is below average by " +String.format("%.1f",absDiff) + " degrees C";
        } 
        return "Temperature and Average is equal"; 
    }

    public void comparePerthTemp(String city, double temp){
        if(!isValidCity(city)){
            throw new IllegalArgumentException("City is unavailable");
        }
        if(!isValidTemp(city, temp)){
            throw new IllegalArgumentException("Not a valid temperature");
        }
        // String result= "";

        double morningDiff= temp- PERTH_MORNING;
        double noonDiff= temp - PERTH_AFTERNOON;
        String morningPrint="";
        String noonPrint="";

        try{
            FileWriter writer= new FileWriter("perthComparison.txt",true);
            if(morningDiff>0){
                morningPrint="Temperature is above the morning average of Perth by " +String.format("%.1f", morningDiff)+ " degrees C...";
            }else if(morningDiff<0){
                morningPrint="Temperature is below the morning average of Perth by " +String.format("%.1f", Math.abs(morningDiff))+ " degrees C...";
            }else{
                morningPrint= "Temperature is equal to morning average of Perth...";
            }
            
            if(noonDiff>0){
                noonPrint="Temperature is above the afternoon average of Perth by "+String.format("%.1f", noonDiff)+ " degree C";
            }else if(noonDiff<0){
                noonPrint="Temperature is below the afternoon average of Perth by "+String.format("%.1f", Math.abs(noonDiff))+ " degree C";
            }else{
            noonPrint="Temperature is equal to the afternoon average of Perth...";
            }
            writer.write("City: "+city+ "\n"+ "Temperature: "+temp+"degrees C\n"+"Morning comparison: "+morningPrint+ "\nAfternoon comparison: " +noonPrint+"\n\n");
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error caused while writing to file");
        }
    }
    public void readPerthFile(){
		try{
            BufferedReader bfReader= new BufferedReader(new FileReader("perthComparison.txt"));
            String line;
			System.out.println("\n==== Perth comparison results ===");

            while((line= bfReader.readLine())!= null){
                System.out.println(line);
            }
            bfReader.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch(IOException e){
            System.out.println("Caused an error while reading file...");
        }
    }
}
