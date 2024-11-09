package com.napier.devops;

import java.util.ArrayList;

/**
 * This class serves as the entry point for the application and generates the various reports
 * for the user.
 */
public class Apps
{
    public static void main(String[] args)
    {
        // Create new CityReport Object Instance
        CityReport ctyReport = new CityReport();

        // Connect to database
        if(args.length < 1){
            ctyReport.connect("localhost:33060", 30000);
        }else{
            ctyReport.connect(args[0], Integer.parseInt(args[1]));
        }

        // Getting all Capital Cities in the World where N is not provided by the user.
        ArrayList<CityReport> CapitalCitiesWorldWide = ctyReport.getCapitalCitiesWorldWide();
        ctyReport.displayCapitalCities(CapitalCitiesWorldWide);
        System.out.println();

        // Getting the top N populated capital cities in the World where N is provided by the user.
        ArrayList<CityReport> topCapitalCitiesWorldWide = ctyReport.getCapitalCitiesWorldwide(10);
        ctyReport.displayCapitalCities(topCapitalCitiesWorldWide);
        System.out.println();

        // Getting the top N populated capital cities in a region where N is provided by the user.
        ArrayList<CityReport> topCapitalCitiesInRegion = ctyReport.getCapitalCitiesInRegion("Central America", 5);
        ctyReport.displayCapitalCities(topCapitalCitiesInRegion);

        System.out.println();

        // Getting all the capital cities in a region organised by largest to smallest.
        ArrayList<CityReport> capitalCitiesInRegion = ctyReport.getCapitalCitiesInRegion("Central America");
        ctyReport.displayCapitalCities(capitalCitiesInRegion);

        System.out.println();

        // Getting the top N populated capital cities in a continent where N is provided by the user.
        ArrayList<CityReport> topcapitalCitiesInContinent = ctyReport.getCapitalCitiesInContinent("Asia", 5);
        ctyReport.displayCapitalCities(topcapitalCitiesInContinent);

        System.out.println();

        // Getting all the capital cities in a region organised by largest to smallest.
        ArrayList<CityReport> capitalCitiesInContinent = ctyReport.getCapitalCitiesInContinent("Asia");
        ctyReport.displayCapitalCities(capitalCitiesInContinent);

        System.out.println();

        // Getting the top N populated cities in the world.
        ArrayList<CityReport> citiesInWorld = ctyReport.getCitiesInWorld();
        ctyReport.displayCities(citiesInWorld);

        System.out.println();

        // Getting the top N populated cities in the world where N is provided by the user.
        ArrayList<CityReport> topCitiesInWorld = ctyReport.getCitiesInWorld(5);
        ctyReport.displayCities(topCitiesInWorld);

        System.out.println();

        // Getting the top N populated cities in a region where N is provided by the user.
        ArrayList<CityReport> topCitiesInRegion = ctyReport.getCitiesInRegion("Central America", 5);
        ctyReport.displayCities(topCitiesInRegion);

        System.out.println();

        // Getting all the cities in a region organised by largest population to smallest.
        ArrayList<CityReport> citiesInRegion = ctyReport.getCitiesInRegion("Central America");
        ctyReport.displayCities(citiesInRegion);

        System.out.println();

        // Getting all populated cities in a district where N is not provided by the user.
        ArrayList<CityReport> ALLCityDistrict = ctyReport.getCitiesInDistrict("Rio de Janeiro");
        ctyReport.displayCities(ALLCityDistrict);
        System.out.println();

        // Getting the top N populated cities in a region where N is provided by the user.
        ArrayList<CityReport> topNDistrict = ctyReport.getCitiesInDistrict("Rio de Janeiro", 10);
        ctyReport.displayCities(topNDistrict);
        System.out.println();

        // Getting the top N populated cities in a country where N is provided by the user.
        ArrayList<CityReport> topCitiesInCountry = ctyReport.getCitiesInCountry("Brazil", 5);
        ctyReport.displayCities(topCitiesInCountry);

        // Getting all populated cities in a country organised by largest population to smallest.
        ArrayList<CityReport> citiesInCountry = ctyReport.getCitiesInCountry("Brazil");
        ctyReport.displayCities(citiesInCountry);

        System.out.println();

        // Getting all populated cities in a continent  organised by largest population to smallest.
        ArrayList<CityReport> AllCityContinent = ctyReport.getCitiesInContinent("Asia");
        ctyReport.displayCities(AllCityContinent);

        System.out.println("\nWith A Limit of 5");

        // Getting all populated cities in a continent  organised by largest population to smallest where there is a limit N.
        ArrayList<CityReport> TopNContinent = ctyReport.getCitiesInContinent("Asia", 5);
        ctyReport.displayCities(TopNContinent);

        // Disconnect from database
        ctyReport.disconnect();
    }
}