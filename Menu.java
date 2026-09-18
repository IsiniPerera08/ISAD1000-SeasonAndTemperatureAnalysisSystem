/*
Name: Isini Ayansa Perera
Student ID: 23601321
Purpose: Main menu file used to run and access the functionalities of the program.

*/
import java.io.*;
import java.util.Scanner;

public class Menu{
    private static Scanner sc= new Scanner(System.in);
    public static void main(String [] args){
        SeasonFinder seasonFind= new SeasonFinder();
        TemperatureChecker checkTemp= new TemperatureChecker();

        int choice= -1;

        while(choice != 0){
            try{
                System.out.println();
                System.out.println("======Seasons and Temperature System======");
                System.out.println("1> Find season- Meteorological seasons");
                System.out.println("2> Find season- Traditional seasons");
                System.out.println("3> Compare seasons");
                System.out.println("4> Compare city average temperature");
                System.out.println("5> Compare temperature of city with Perth");
                System.out.println("6> View perth comparison txt file");
                System.out.println("0> Exit");

                choice= readInt("Enter choice: ");

                if(choice==1){  //meteorological season
                
                    System.out.print("Enter country: ");
                    String country= sc.nextLine();
                    if(country.trim().isEmpty()){
                        throw new IllegalArgumentException("Country cannot be empty");
                    }
                    System.out.print("Enter month(number): ");
                    String monthInput= sc.nextLine();
                    if(monthInput.trim().isEmpty()){
                        throw new IllegalArgumentException("Month cannot be empty...");
                    }int month;
                    try{
                        month= Integer.parseInt(monthInput);
                    }catch(NumberFormatException e){
                        throw new IllegalArgumentException("Invalid month...");
                    }

                    String result= seasonFind.findMeteorologicalSeason(country, month);
                    System.out.println("Season: "+result);
                }
                else if(choice==2){ //traditional season
                    System.out.print("Enter country: ");
                    String country= sc.nextLine();
                    if(country.trim().isEmpty()){
                        throw new IllegalArgumentException("Country cannot be empty");
                    }
                    System.out.print("Enter month(number): ");
                    String monthInput= sc.nextLine();
                    if(monthInput.trim().isEmpty()){
                        throw new IllegalArgumentException("Month cannot be empty...");
                    }int month;
                    try{
                        month= Integer.parseInt(monthInput);
                    }catch(NumberFormatException e){
                        throw new IllegalArgumentException("Invalid month...");
                    }

                    String result= seasonFind.findTraditionalSeason(country, month);
                    System.out.println("Season: "+result);
                }
                else if(choice==3){
                    System.out.print("Enter 1st country: ");
                    String country1 = sc.nextLine();
                    if(country1.trim().isEmpty()){
                        throw new IllegalArgumentException("Country cannot be empty");
                    }
                    System.out.print("Enter 2nd country: ");
                    String country2= sc.nextLine();
                    if(country2.trim().isEmpty()){
                        throw new IllegalArgumentException("Country cannot be empty");
                    }
                    System.out.print("Enter month (number): ");
                    String monthInput= sc.nextLine();
                    if(monthInput.trim().isEmpty()){
                        throw new IllegalArgumentException("Month cannot be empty...");
                    }int month;
                    try{
                        month= Integer.parseInt(monthInput);
                    }catch(NumberFormatException e){
                        throw new IllegalArgumentException("Invalid month...");
                    }

                    String result= seasonFind.countrySeasonCompare(country1, country2, month);
                    System.out.println("Result: "+result);
                }
                else if(choice==4){
                    System.out.print("Enter city: ");
                    String city= sc.nextLine();
                    if(city.trim().isEmpty()){
                        throw new IllegalArgumentException("City cannot be empty");
                    }
                    System.out.print("Enter temperature: ");
                    String tempInput= sc.nextLine();
                    if(tempInput.trim().isEmpty()){
                        throw new IllegalArgumentException("Temperature cannot be empty");
                    }double temperature;
                    try{
                        temperature =Double.parseDouble(tempInput);
                    }catch(NumberFormatException e){
                        throw new IllegalArgumentException("Invalid temperature...");
                    }
                    System.out.print("Enter time(morning or afternoon): ");
                    String time= sc.nextLine();
                    if(time.trim().isEmpty()){
                        throw new IllegalArgumentException("Time cannot be empty...");
                    }
                    String result= checkTemp.compareAvgTemp(city, temperature, time);
                    System.out.println("Result: "+result);
                }   
                else if(choice==5){ //compare with perth
                    System.out.print("Enter city: ");
                    String city= sc.nextLine();
                    if(city.trim().isEmpty()){
                        throw new IllegalArgumentException("City cannot be empty");
                    }
                    System.out.print("Enter temperature: ");
                    String tempInput= sc.nextLine();
                    if(tempInput.trim().isEmpty()){
                        throw new IllegalArgumentException("Temperature cannot be empty");
                    }double temperature;
                    try{
                        temperature =Double.parseDouble(tempInput);
                    }catch(NumberFormatException e){
                        throw new IllegalArgumentException("Invalid temperature...");
                    }
                    checkTemp.comparePerthTemp(city, temperature);
                    System.out.println("Comparison was saved to \"perthComparison.txt\"  file");
                }
                else if(choice ==6){
                    checkTemp.readPerthFile();
                }
                else if(choice==0){
                    System.out.println("Exiting the program, Have a nice day!");
                }
                else{
                    System.out.println("Invalid choice, enter between 1 and 6...");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

        private static int readInt(String prompt) {
            while(true){
                System.out.print(prompt);
                String line= sc.nextLine().trim();
                try{
                return Integer.parseInt(line);
                }catch(NumberFormatException e){
                    System.out.println("Enter a number...");
                }
            }
        }
	
// 	public void readPerthFile(){
// 		try{
// 			File file= new File("perthComparison.txt");
// 			Scanner fileReader= new Scanner(file);

// 			while(fileReader.hasNextLine()){
// 				System.out.println(fileReader.nextLine());
// 			}
// 			fileReader.close();
// 		}catch(FileNotFoundException e){
// 			System.out.println("File not found...");
// 	}
// }
}
