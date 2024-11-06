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

        // Get all capital cities in a continent ordered by population
        ArrayList<CityReport> capitalCitiesInContinent = ctyReport.getCapitalCitiesInContinent("Asia", 5);
        ctyReport.displayCapitalCities(capitalCitiesInContinent);

        // Disconnect from database
        ctyReport.disconnect();
    }
}