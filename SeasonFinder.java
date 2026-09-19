public class SeasonFinder{
  public boolean isValidCountry(String country){
    String[] isCountries ={"australia","spain","japan","mauritius","malaysia","sri lanka"};
    
    String countryLow= country.toLowerCase();
    for(String valid : isCountries){
      if(valid.equals(countryLow)){
        return true;
      }
    }
    return false;
  }
  
  public boolean isValidMonth(int month){
    return month >= 1 && month <=12;
  }
  
  public String findMeteorologicalSeason(String country, int month){
    if(!isValidCountry(country)){
      throw new IllegalArgumentException("Country is unavailable");
    }
    if(!isValidMonth(month)){
      throw new IllegalArgumentException("Invalid month... Enter month between 1 and 12");
    }
    
    String countryLow= country.toLowerCase();
    
    
    //AUSTRALIA- METEOROLOGICAL SEASONS
    if(countryLow.equals("australia")){
      if(month==12 || month == 1 || month == 2){
        return "Summer";
      }
      if(month ==3 || month==4 || month== 5){
        return "Autumn";
      }
      if(month== 6 || month == 7 ||month == 8){
        return "Winter";
      }
      if(month==9 || month ==10 || month==11){
        return "Spring";
      }
    }
    
    //SPAIN & JAPAN= METEOROLOGICAL SEASONS
    if(countryLow.equals("spain") || countryLow.equals("japan")){
      if(month==12 || month == 1 || month == 2){
        return "Winter";
      }
      if(month ==3 || month==4 || month== 5){
        return "Spring";
      }
      if(month== 6 || month == 7 ||month == 8){
        return "Summer";
      }
      if(month==9 || month ==10 || month==11){
        return "Autumn";
      }
    }
    
    //MAURITIUS= METEOROLOGICAL SEASON
    if(countryLow.equals("mauritius")){
      if(month==11 || month == 12 || month == 1||  month == 2 ||  month == 3 ||  month == 4){
        return "Summer";
      }
      if(month== 5){
        return "Autumn";
      }
      if(month== 6 || month == 7 ||month == 8 ||  month == 9){
        return "Winter";
      }
      if(month==10){
        return "Spring";
      }
    }

    if(countryLow.equals("malaysia") || countryLow.equals("sri lanka")){
      if(month == 12 || month == 1||  month == 2){
        return "Northeast Monsoon";
      }
      if(month== 3 || month == 4){
        return "Inter Monsoon";
      }
      if(month== 5 || month == 6 || month==7 ||month==8||month==9){
        return "Southeast Monsoon";
      }
      if(month==10 || month == 11){
        return "Inter Monsoon";
      }
    }
    return "Unable to find season";
  }

  public String findTraditionalSeason(String country, int month){
    if(!isValidCountry(country)){
      throw new IllegalArgumentException("Country is unavailable");
    }
    if(!isValidMonth(month)){
      throw new IllegalArgumentException("Invalid month... Enter month between 1 and 12");
    }

    String countryLow= country.toLowerCase();

    if(countryLow.equals("australia")){
      if(month==12 || month==1){
        return "Birak";
      }
      if(month==2 || month == 3){
        return "Bunuru";
      }
      if(month==4 || month == 5){
        return "Djeran";
      }
      if(month== 6 || month ==7){
        return "Makuru";
      }
      if(month==8 || month ==9){
        return "Dijiba";
      }
      if(month== 10 || month==11){
        return "Kambarang";
      }
    }
    return "Unable to find traditional season for "+country;
  }

  public String countrySeasonCompare(String country1, String country2, int month){
    if(!isValidCountry(country2) || !isValidCountry(country1)){
      throw new IllegalArgumentException("Country unavailable");
    }
    if(!isValidMonth(month)){
      throw new IllegalArgumentException("Month is invalid");
    }

    String season1= findMeteorologicalSeason(country1, month);
    String season2= findMeteorologicalSeason(country2, month);
    //String result= "";

    // result+= country1 + " season is: " +season1+ "\n";
    // result += country2 + "season is: " +season2+ "\n";

    if(season1.equalsIgnoreCase(season2)){
      return country1+ " and "  +country2+ " have the SAME season";
    }
    else{
      return country1+ " and "  +country2+ " don't have the SAME season";
    }
  }
}

