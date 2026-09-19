import java.io.*;

public class TemperatureTestHarness{
    static TemperatureChecker tempCheck= new TemperatureChecker();
    static int passed= 0;
    static int failed= 0;

    static void test(String testID, Object expected, Object actual){
        if(expected.equals(actual)){
            System.out.println("Passed: "+testID+"... "+expected);
            passed++;
        }else{
            System.out.println("Failed: "+testID);
            System.out.println("Expected: "+expected);
            System.out.println("Actual: " +actual);
            failed++;
        }
    }

    static void testValidCity(){
        System.out.println("\n==== Testing isValidCity() ====");

        test("Test1", true, tempCheck.isValidCity("brisbane"));
        test("Test2", true, tempCheck.isValidCity("perth"));
        test("Test3", false, tempCheck.isValidCity("colombo"));
        test("Test4", false, tempCheck.isValidCity(""));
        test("Test5", true, tempCheck.isValidCity("HOBART"));
    }

    static void testValidTemp(){
        System.out.println("\n==== Testing isValidTemp() ====");

        test("Test6", true, tempCheck.isValidTemp("brisbane", 35.0));
        test("Test7", true, tempCheck.isValidTemp("perth", 35.0));
        test("Test8", true, tempCheck.isValidTemp("hobart", 20.0));
        test("Test9", false, tempCheck.isValidTemp("colombo", 25.0));
        test("Test10", false, tempCheck.isValidTemp("perth", 78.8));
        test("Test11", false, tempCheck.isValidTemp("hobart", -10.2));
    }

    static void testCompareAvgTemp(){
        System.out.println("\n==== Testing compareAvgTemp() ====");

        test("Test12", "Temperature is above average by 8.2 degrees C and difference is greater than 6 degrees C", tempCheck.compareAvgTemp("brisbane", 30, "morning"));
        test("Test13", "Temperature is above average by 2.0 degrees C",tempCheck.compareAvgTemp("perth", 25, "afternoon"));
        test("Test14", "Temperature is below average by 13.0 degrees C and difference is greater than 6 degrees C", tempCheck.compareAvgTemp("perth", 10, "afternoon"));
        test("Test15", "Temperature is below average by 5.1 degrees C", tempCheck.compareAvgTemp("hobart", 10, "afternoon"));
        test("Test16", "Temperature and Average is equal", tempCheck.compareAvgTemp("perth", 23.0, "afternoon"));
        try{
            tempCheck.compareAvgTemp("colombo", 20, "morning");
            System.out.println("Failed Test17");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed Test17");
            passed++;
        }try{
            tempCheck.compareAvgTemp("brisbane", 20 ,"midnight");
            System.out.println("Failed Test18");
            failed++;
        }
        catch(IllegalArgumentException e){
            System.out.println("Passed Test18");
            passed++;
        }try{
            tempCheck.compareAvgTemp("perth", 60, "morning");
            System.out.println("Failed Test19");
            failed++;
        }
        catch(IllegalArgumentException e){
            System.out.println("Passed Test19");
            passed++;
        }  
    }


    static void testComparePerthTemp(){
        System.out.println("\n==== Testing comparePerthTemp() ====");
        try{
            tempCheck.comparePerthTemp("perth", 25);
            System.out.println("Passed Test20");
            passed++;
        }catch(Exception e){
            System.out.println("Failed Test20 " +e.getMessage());
            failed++;
        }try{
            tempCheck.comparePerthTemp("hobart", 10);
            System.out.println("Passed Test21");
            passed++;
        }catch(Exception e){
            System.out.println("Failed Test21 " +e.getMessage());
            failed++;

        }try{
            tempCheck.comparePerthTemp("colombo", 25);
            System.out.println("Failed Test22");
            failed++;
        }catch(IllegalArgumentException e){
            System.out.println("Passed Test22");
            passed++;
        }
        try{
            tempCheck.comparePerthTemp("perth", 70);
            System.out.println("Failed Test23");
            failed++;
        }
        catch(IllegalArgumentException e){
            System.out.println("Passed Test23");
            passed++;
        }
    }

    static void testValidTempBVA(){
        System.out.println("\n====Testing isValidTemp()-BVA====");

        test("Test24", true, tempCheck.isValidTemp("brisbane", 35.0));
        test("Test25", true, tempCheck.isValidTemp("perth", 30.0));
        test("Test26", true, tempCheck.isValidTemp("hobart", 20.0));
        test("Test27", false, tempCheck.isValidTemp("colombo", 25.0));
        test("Test28", false, tempCheck.isValidTemp("perth", 78.8));
        test("Test29", false, tempCheck.isValidTemp("hobart", -10.2));

        test("Test30", true, tempCheck.isValidTemp("perth", 0.7));
        test("Test31", true, tempCheck.isValidTemp("perth", 46.0));
        test("Test32", false, tempCheck.isValidTemp("perth", 0.1));
        test("Test33", false, tempCheck.isValidTemp("perth", 90.0));

        test("Test34", false, tempCheck.isValidTemp("perth", 321.0));
    }


    static void testReadPerthFileWhiteBox(){
        System.out.println("\n====readPerthFile()-WB(try-catch path)====");
        try{
            FileWriter writer= new FileWriter("perthComparison.txt");
            writer.write("Test for the white box test\n");
            writer.close(); //saves the file

            tempCheck.readPerthFile(); //reads file
            System.out.println("Passed Test1(WB)");
            passed++;
        }catch(Exception e){
            System.out.println("Failed Test1(WB): " +e.getMessage());
            failed++;
        }
        try{
            File file= new File("perthComparison.txt");
            file.delete();      
            tempCheck.readPerthFile(); 
            System.out.println("Passed Test2(WB)");
            passed++;
        }catch(Exception e){
            System.out.println("Failed Test2(WB): " +e.getMessage());
            failed++;
        }
    }

    public static void main(String[] args){
        System.out.println("\n===== Test harness for TemperatureChecker.java =====\n");

        testValidCity();
        testValidTemp();
        testCompareAvgTemp();
        testComparePerthTemp();
        testValidTempBVA();
        testReadPerthFileWhiteBox();

        System.out.println("\n=====================\n");
        System.out.println("Total passes: " +passed);
        System.out.println("Total fails: " +failed);
        System.out.println("Total test cases: " +(passed+failed));
        System.out.println("=====================");
    }
}
