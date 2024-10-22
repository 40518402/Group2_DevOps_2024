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
        CountryReport ctryReport = new CountryReport();

        // Connect to database
        ctryReport.connect();

        // Getting Top Countries By Continent
        ArrayList<CountryReport> countries = ctryReport.getTopCountriesByContinent("Asia", 10);
        ctryReport.displayCountries(countries);

        System.out.println();

        // Getting All Countries In a Region
        ArrayList<CountryReport> region = ctryReport.getCountriesByRegion("Caribbean");
        ctryReport.displayCountries(region);

        System.out.println();

        ArrayList<CountryReport> topCountiresByRegion = ctryReport.getTopCountiresByRegion("Caribbean", 10);
        ctryReport.displayCountries(topCountiresByRegion);

        // Get the top N populated countries in the world where N is provided by the user.
        ArrayList<CountryReport> get_countries_InWorld = ctryReport.get_countries_InWorld("Countries", 10);
        ctryReport.displayCountries(get_countries_InWorld);

        System.out.println();
        // Disconnect from database
        ctryReport.disconnect();
    }
}