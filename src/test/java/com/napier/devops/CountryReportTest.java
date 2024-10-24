package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CountryReportTest
{
    static CountryReport countryReport;

    @BeforeAll
    static void init()
    {
        countryReport = new CountryReport();
    }

    @Test
    void displayCountriesTestNull()
    {
        countryReport.displayCountries(null);
    }

    @Test
    void displayCountriesTestEmpty()
    {
        ArrayList<CountryReport> countries = new ArrayList<CountryReport>();
        countryReport.displayCountries(countries);
    }

    @Test
    void displayCountriesTestContainsNull()
    {
        ArrayList<CountryReport> countries = new ArrayList<CountryReport>();
        countries.add(null);
        countryReport.displayCountries(countries);
    }

    @Test
    void displayCountries()
    {
        ArrayList<CountryReport> countries = new ArrayList<CountryReport>();
        CountryReport country = new CountryReport();
        country.setCode("BLZ");
        country.setName("Belize");
        country.setContinent("North America");
        country.setRegion("Central America");
        country.setPopulation(241000);
        country.setCapital("Belmopan");
        countries.add(country);
        countryReport.displayCountries(countries);
    }
}