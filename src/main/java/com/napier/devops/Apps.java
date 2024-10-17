package com.napier.devops;

//import java.sql.*;
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

        // Getting All Countries In a Region
        ArrayList<CountryReport> region = ctryReport.getCountriesByRegion("Caribbean");
        ctryReport.displayCountries(region);

        // Disconnect from database
        ctryReport.disconnect();
    }
}