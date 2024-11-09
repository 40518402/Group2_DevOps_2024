package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CountryReportUnitTest
{
    static CountryReport countryReport;

    @BeforeAll
    static void init() {

        countryReport = new CountryReport();
    }

    @Test
    void countryGettersTest() {
        CountryReport belize = new CountryReport();
        belize.setCode("BLZ");
        belize.setName("Belize");
        belize.setContinent("North America");
        belize.setRegion("Central America");
        belize.setPopulation(241000);
        belize.setCapital("Belmopan");

        assertEquals("BLZ", belize.getCode());
        assertEquals("Belize", belize.getName());
        assertEquals("North America", belize.getContinent());
        assertEquals("Central America", belize.getRegion());
        assertEquals(241000, belize.getPopulation());
        assertEquals("Belmopan", belize.getCapital());
    }

    @Test
    void displayCountriesTestNull() {
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
    void displayCountriesTestNormal()
    {
        ArrayList<CountryReport> countries = new ArrayList<CountryReport>();
        CountryReport belize = new CountryReport();
        belize.setCode("BLZ");
        belize.setName("Belize");
        belize.setContinent("North America");
        belize.setRegion("Central America");
        belize.setPopulation(241000);
        belize.setCapital("Belmopan");
        countries.add(belize);
        countryReport.displayCountries(countries);
    }
}