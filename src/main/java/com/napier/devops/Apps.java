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

        // Getting the top N populated capital cities in a region where N is provided by the user.
        ArrayList<CityReport> topCapitalCitiesInRegion = ctyReport.getCapitalCitiesInRegion("Central America", 5);
        ctyReport.displayCapitalCities(topCapitalCitiesInRegion);

        System.out.println();

        // Getting all the capital cities in a region organised by largest to smallest.
        ArrayList<CityReport> capitalCitiesInRegion = ctyReport.getCapitalCitiesInRegion("Central America");
        ctyReport.displayCapitalCities(capitalCitiesInRegion);

        // Disconnect from database
        ctyReport.disconnect();
    }
}