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

        // Disconnect from database
        ctyReport.disconnect();
    }
}