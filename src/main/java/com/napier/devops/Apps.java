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

        // Getting the top N populated countries in a continent where N is provided by the user.
        ArrayList<CountryReport> topCountries = ctryReport.getCountriesByContinent("Asia", 10);
        ctryReport.displayCountries(topCountries);

        System.out.println();

        // Getting all the countries in a continent organised by largest population to smallest.
        ArrayList<CountryReport> countries = ctryReport.getCountriesByContinent("Asia");
        ctryReport.displayCountries(countries);

        System.out.println();

        // Getting All Countries In a Region
        ArrayList<CountryReport> region = ctryReport.getCountriesByRegion("Caribbean");
        ctryReport.displayCountries(region);

        System.out.println();

        // Getting the top N populated countries in a region where N is provided by the user.
        ArrayList<CountryReport> topCountriesByRegion = ctryReport.getTopCountriesByRegion("Caribbean", 10);
        ctryReport.displayCountries(topCountriesByRegion);

        System.out.println();

        // Get the top N populated countries in the world where N is provided by the user.
        ArrayList<CountryReport> topCountriesInWorld = ctryReport.getCountriesInWorld(10);
        ctryReport.displayCountries(topCountriesInWorld);

        System.out.println();

        // Get all the countries in the world organised by largest population to smallest.
        ArrayList<CountryReport> countriesInWorld = ctryReport.getCountriesByWorldPopulation();
        ctryReport.displayCountries(countriesInWorld);

        // Disconnect from database
        ctryReport.disconnect();
    }
}