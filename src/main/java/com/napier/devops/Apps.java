package com.napier.devops;

//import java.sql.*;
import java.util.ArrayList;

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

        // Disconnect from database
        ctryReport.disconnect();
    }
}