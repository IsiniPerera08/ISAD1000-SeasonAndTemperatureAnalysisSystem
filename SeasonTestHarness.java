/*
Name: Isini Ayansa Perera
Student ID: 23601321
Purpose: test harness file used to implement and execute test cases related to the SeasonFinder methods
*/

public class SeasonTestHarness {
    static SeasonFinder seasonFind= new SeasonFinder();
    static int passed= 0;
    static int failed= 0;

    static void test(String testID, Object expected, Object actual){
        if(expected.equals(actual)){
            System.out.println("Passed: " +testID+ "... Expected: "+expected);
            passed++;
        }else{
            System.out.println("Failed: "+testID);
            System.out.println("Expected: " +expected);
            System.out.println("Actual: "+actual);
            failed++;
        }
    }

    static void testValidCountry(){
        System.out.println("\n====Testing isValidCountry()====");

        test("Test1", true, seasonFind.isValidCountry("sri lanka"));
        test("Test2", true, seasonFind.isValidCountry("SRI LANKA"));
        test("Test3", false, seasonFind.isValidCountry("Perera"));
        test("Test4", false, seasonFind.isValidCountry("italy"));
        test("Test5",false, seasonFind.isValidCountry(""));
    }

    static void testValidMonth(){
        System.out.println("\n====Testing isValidMonth()====");

        test("Test6", true, seasonFind.isValidMonth(10));
        test("Test7", true, seasonFind.isValidMonth(5));
        test("Test8", false, seasonFind.isValidMonth(15));
        test("Test9", false, seasonFind.isValidMonth(0));
        test("Test10", false, seasonFind.isValidMonth(-10));
        test("Test11", false, seasonFind.isValidMonth(321));
        test("Test12", true, seasonFind.isValidMonth(1));
        test("Test13", true, seasonFind.isValidMonth(12));
        test("test14", false, seasonFind.isValidMonth(0));
        test("Test15", false, seasonFind.isValidMonth(13));
    }


    static void testMeteorologicalSeason(){
        System.out.println("\n====Testing findMeteorologicalSeason()====");
        
        test("T13", "Northeast Monsoon", seasonFind.findMeteorologicalSeason("sri lanka", 12));
        test("T14", "Winter", seasonFind.findMeteorologicalSeason("japan", 1));
        test("T15","Inter Monsoon", seasonFind.findMeteorologicalSeason("malaysia", 4));
        test("T16", "Autumn", seasonFind.findMeteorologicalSeason("spain", 9));
        test("T17", "Spring", seasonFind.findMeteorologicalSeason("australia", 10));
        test("T18", "Summer", seasonFind.findMeteorologicalSeason("mauritius", 3));
        try{
            seasonFind.findMeteorologicalSeason("italy", 5);
            System.out.println("Failed T19");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed T19");
            passed++;
        }
        try{
            seasonFind.findMeteorologicalSeason("Perera", 3);
            System.out.println("Failled T20");
            failed++;
        }
        catch(IllegalArgumentException e){
            System.out.println("Passed T20");
            passed++;
        }
    }

    static void testTraditionalSeason(){
        System.out.println("\n==== findTraditionalSeason() ====");

        test("T21", "Birak", seasonFind.findTraditionalSeason("australia", 1));
        test("T22", "Djeran", seasonFind.findTraditionalSeason("australia", 5));
        test("T23", "Unable to find traditional season for japan", seasonFind.findTraditionalSeason("japan", 6));
        try{
            seasonFind.findTraditionalSeason("france", 3);
            System.out.println("Failed T24");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed T24");
            passed++;
        }
        try{
            seasonFind.findTraditionalSeason("australia", 25);
            System.out.println("Failed T25");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed T25");
            passed++;
        }
    }

    static void testCountrySeasonCompare(){
        System.out.println("\n====Testing countrySeasonCompare====");
        test("T26", "malaysia and sri lanka have the SAME season" ,seasonFind.countrySeasonCompare("malaysia","sri lanka", 10));
        test("T27", "sri lanka and japan don't have the SAME season", seasonFind.countrySeasonCompare("sri lanka", "japan", 10));
        try{
            seasonFind.countrySeasonCompare("italy", "japan", 5);
            System.out.println("Failed");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed");
            passed++;
        }
        try{
            seasonFind.countrySeasonCompare("malaysia", "sri lanka", 25);
            System.out.println("Failed");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed");
            passed++;
        }
    }

    static void testValidCountryWhiteBox(){
        System.out.println("\n====isValidCountry()-whitebox(for loop path)====");

        test("Test1(WB)", true, seasonFind.isValidCountry("australia"));
        test("Test2(WB)", true, seasonFind.isValidCountry("sri lanka"));
        test("test3(WB)", false, seasonFind.isValidCountry("Perera"));
        test("test4(WB)", false, seasonFind.isValidCountry(""));
    }


    public static void main(String[]args){
        System.out.println("\n===== Test harness for SeasonFinder.java =====\n");

        testValidCountry();
        testValidMonth();
        testMeteorologicalSeason();
        testTraditionalSeason();
        testCountrySeasonCompare();
        testValidCountryWhiteBox();

        System.out.println("\n=====================");
        System.out.println("Total passes: "+passed);
        System.out.println("Total fails: "+failed);
        System.out.println("Total test Cases: " +(passed+ failed));
        System.out.println("\n=====================");
    }
}

