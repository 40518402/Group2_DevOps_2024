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
        // Create new CountryReport Object Instance
        CityReport ctyReport = new CityReport();

        // Connect to database
        if(args.length < 1){
            ctyReport.connect("localhost:33060", 30000);
        }else{
            ctyReport.connect(args[0], Integer.parseInt(args[1]));
        }

        // Getting the top N populated cities in a region where N is provided by the user.
        ArrayList<CityReport> topCities = ctyReport.getCitiesInRegion("Central America", 5);
        ctyReport.displayCities(topCities);

        System.out.println();

        // Getting all the cities in a region organised by largest population to smallest.
        ArrayList<CityReport> cities = ctyReport.getCitiesInRegion("Central America");
        ctyReport.displayCities(cities);

        System.out.println();

        // Disconnect from database
        ctyReport.disconnect();
    }
}