package com.napier.devops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountryReportIntegrationTest
{
    static CountryReport countryReport;

    @BeforeAll
    static void init()
    {
        countryReport = new CountryReport();
        countryReport.connect("localhost:33060", 30000);

        assertNotNull(countryReport.getDatabaseConnection());
    }

    @AfterAll
    static void close() {
        countryReport.disconnect();
    }

    @Test
    void getCountriesInWorldAllCountriesNormalTest() {
        ArrayList<CountryReport> countriesInWorld = countryReport.getCountriesInWorld();
        assertNotNull(countriesInWorld, "Countries list should not be null.");

        CountryReport firstCountry = countriesInWorld.get(0);

        assertEquals(239, countriesInWorld.size());
        assertEquals("CHN", firstCountry.getCode());
        assertEquals("China", firstCountry.getName());
        assertEquals("Asia", firstCountry.getContinent());
        assertEquals("Eastern Asia", firstCountry.getRegion());
        assertEquals(1277558000, firstCountry.getPopulation());
        assertEquals("Peking", firstCountry.getCapital());
    }

    @Test
    void getCountriesInWorldNormalTest() {
        ArrayList<CountryReport> topCountriesInWorld = countryReport.getCountriesInWorld(10);

        assertEquals(10, topCountriesInWorld.size());
    }

    @Test
    void getCountriesInWorldNullNumberTest() {
        ArrayList<CountryReport> countriesInWorld = countryReport.getCountriesInWorld(null);

        assertEquals(239, countriesInWorld.size());
    }

    @Test
    void getCountriesInWorldNegativeNumberTest() {
        ArrayList<CountryReport> countriesInWorld = countryReport.getCountriesInWorld(-5);

        assertNull(countriesInWorld);
    }

    @Test
    void getCountriesInWorldZeroTest() {
        ArrayList<CountryReport> countriesInWorld = countryReport.getCountriesInWorld(0);

        assertEquals(0, countriesInWorld.size());
    }

    @Test
    void getCountriesContinentNormalTest()
    {
        ArrayList<CountryReport> countriesInContinent = countryReport.getCountriesInContinent("Asia");
        assertNotNull(countriesInContinent, "Countries list should not be null.");

        CountryReport firstCountry = countriesInContinent.get(0);

        assertEquals("CHN", firstCountry.getCode());
        assertEquals("China", firstCountry.getName());
        assertEquals("Asia", firstCountry.getContinent());
        assertEquals("Eastern Asia", firstCountry.getRegion());
        assertEquals(1277558000, firstCountry.getPopulation());
        assertEquals("Peking", firstCountry.getCapital());
    }

    @Test
    void getCountriesInContinentNoneExistentContinentTest() {
        ArrayList<CountryReport> countriesInContinent = countryReport.getCountriesInContinent("NoneExistentContinent");

        assertEquals(0, countriesInContinent.size());
    }

    @Test
    void getCountriesInContinentNullContinentTest() {
        ArrayList<CountryReport> countriesInContinent = countryReport.getCountriesInContinent(null);

        assertEquals(0, countriesInContinent.size());
    }

    @Test
    void getCountriesInContinentNormalTest() {
        ArrayList<CountryReport> topCountriesInContinent = countryReport.getCountriesInContinent("Asia",10);

        assertEquals(10, topCountriesInContinent.size());
    }

    @Test
    void getCountriesInContinentNegativeNumberTest() {
        ArrayList<CountryReport> topCountriesInContinent = countryReport.getCountriesInContinent("Asia",-5);

        assertNull(topCountriesInContinent);
    }

    @Test
    void getCountriesInContinentZeroTest() {
        ArrayList<CountryReport> countriesInContinent = countryReport.getCountriesInContinent("Asia",0);

        assertEquals(0, countriesInContinent.size());
    }

    @Test
    void getCountriesRegionNormalTest()
    {
        ArrayList<CountryReport> countriesInRegion = countryReport.getCountriesInRegion("Caribbean");
        assertNotNull(countriesInRegion, "Countries list should not be null.");

        CountryReport firstCountry = countriesInRegion.get(0);

        assertEquals("CUB", firstCountry.getCode());
        assertEquals("Cuba", firstCountry.getName());
        assertEquals("North America", firstCountry.getContinent());
        assertEquals("Caribbean", firstCountry.getRegion());
        assertEquals(11201000, firstCountry.getPopulation());
        assertEquals("La Habana", firstCountry.getCapital());
    }

    @Test
    void getCountriesInRegionNoneExistentRegionTest() {
        ArrayList<CountryReport> countriesInRegion = countryReport.getCountriesInRegion("NoneExistentRegion");

        assertEquals(0, countriesInRegion.size());
    }

    @Test
    void getCountriesInRegionNullRegionTest() {
        ArrayList<CountryReport> countriesInRegion = countryReport.getCountriesInRegion(null);

        assertEquals(0, countriesInRegion.size());
    }

    @Test
    void getCountriesInRegionNormalTest() {
        ArrayList<CountryReport> topCountriesInRegion = countryReport.getCountriesInRegion("Caribbean",5);

        assertEquals(5, topCountriesInRegion.size());
    }

    @Test
    void getCountriesInRegionNegativeNumberTest() {
        ArrayList<CountryReport> topCountriesInRegion = countryReport.getCountriesInRegion("Caribbean",-5);

        assertNull(topCountriesInRegion);
    }

    @Test
    void getCountriesInRegionZeroTest() {
        ArrayList<CountryReport> topCountriesInRegion = countryReport.getCountriesInRegion("Caribbean",0);

        assertEquals(0, topCountriesInRegion.size());
    }
}