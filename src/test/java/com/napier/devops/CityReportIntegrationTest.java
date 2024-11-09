package com.napier.devops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CityReportIntegrationTest {
    static CityReport cityReport;

    @BeforeAll
    static void init()
    {
        cityReport = new CityReport();
        cityReport.connect("localhost:33060", 30000);

    }

    @AfterAll
    static void close() {
        cityReport.disconnect();
    }

    @Test
    void getAllCitiesInWorldNormalTest() {
        ArrayList<CityReport> citiesInWorld = cityReport.getCitiesInWorld();
        assertNotNull(citiesInWorld, "Cities list should not be null.");

        CityReport firstCity = citiesInWorld.get(0);
        assertEquals(1024, firstCity.getId());
        assertEquals("Mumbai (Bombay)", firstCity.getName());
        assertEquals("India", firstCity.getCountry());
        assertEquals("Maharashtra", firstCity.getDistrict());
        assertEquals(10500000, firstCity.getPopulation());
    }

    @Test
    void getCitiesInWorldNormalTest() {
        ArrayList<CityReport> topCitiesInWorld = cityReport.getCitiesInWorld(10);

        assertEquals(10, topCitiesInWorld.size());
    }

    @Test
    void getCitiesInWorldNullNumberTest() {
        ArrayList<CityReport> citiesInWorld = cityReport.getCitiesInWorld(null);

        assertEquals(4079, citiesInWorld.size());
    }

    @Test
    void getCitiesInWorldNegativeNumberTest() {
        ArrayList<CityReport> citiesInWorld = cityReport.getCitiesInWorld(-5);

        assertNull(citiesInWorld);
    }

    @Test
    void getCitiesInWorldZeroTest() {
        ArrayList<CityReport> citiesInWorld = cityReport.getCitiesInWorld(0);

        assertEquals(0, citiesInWorld.size());
    }

    @Test
    void getAllCitiesInContinentNormalTest()
    {
        ArrayList<CityReport> citiesInContinent = cityReport.getCitiesInContinent("Asia");
        assertNotNull(citiesInContinent, "Cities list should not be null.");

        CityReport firstCity = citiesInContinent.get(0);
        assertEquals(1024, firstCity.getId());
        assertEquals("Mumbai (Bombay)", firstCity.getName());
        assertEquals("India", firstCity.getCountry());
        assertEquals("Maharashtra", firstCity.getDistrict());
        assertEquals(10500000, firstCity.getPopulation());
    }

    @Test
    void getCitiesInContinentNoneExistentContinentTest() {
        ArrayList<CityReport> citiesInContinent = cityReport.getCitiesInContinent("NoneExistentContinent");

        assertEquals(0, citiesInContinent.size());
    }

    @Test
    void getCitiesInContinentNullContinentTest() {
        ArrayList<CityReport> citiesInContinent = cityReport.getCitiesInContinent(null);

        assertEquals(0, citiesInContinent.size());
    }

    @Test
    void getCitiesInContinentNormalTest() {
        ArrayList<CityReport> topCitiesInContinent = cityReport.getCitiesInContinent("Asia",10);

        assertEquals(10, topCitiesInContinent.size());
    }

    @Test
    void getCitiesInContinentNegativeNumberTest() {
        ArrayList<CityReport> topCitiesInContinent = cityReport.getCitiesInContinent("Asia",-5);

        assertNull(topCitiesInContinent);
    }

    @Test
    void getCitiesInContinentZeroTest() {
        ArrayList<CityReport> citiesInWorld = cityReport.getCitiesInContinent("Asia",0);

        assertEquals(0, citiesInWorld.size());
    }

    @Test
    void getAllCitiesRegionNormalTest()
    {
        ArrayList<CityReport> citiesInRegion = cityReport.getCitiesInRegion("Caribbean");
        assertNotNull(citiesInRegion, "Cities list should not be null.");

        CityReport firstCity = citiesInRegion.get(0);
        assertEquals(2413,firstCity.getId());
        assertEquals("La Habana", firstCity.getName());
        assertEquals("Cuba", firstCity.getCountry());
        assertEquals("La Habana", firstCity.getDistrict());
        assertEquals(2256000,firstCity.getPopulation());
    }

    @Test
    void getCitiesInRegionNoneExistentRegionTest() {
        ArrayList<CityReport> citiesInRegion = cityReport.getCitiesInRegion("NoneExistentRegion");

        assertEquals(0, citiesInRegion.size());
    }

    @Test
    void getCitiesInRegionNullRegionTest() {
        ArrayList<CityReport> citiesInRegion = cityReport.getCitiesInRegion(null);

        assertEquals(0, citiesInRegion.size());
    }

    @Test
    void getCitiesInRegionNormalTest() {
        ArrayList<CityReport> topCitiesInRegion = cityReport.getCitiesInRegion("Caribbean",5);

        assertEquals(5, topCitiesInRegion.size());
    }

    @Test
    void getCitiesInRegionNegativeNumberTest() {
        ArrayList<CityReport> topCitiesInRegion = cityReport.getCitiesInRegion("Caribbean",-5);

        assertNull(topCitiesInRegion);
    }

    @Test
    void getCitiesInRegionZeroTest() {
        ArrayList<CityReport> topCitiesInRegion = cityReport.getCitiesInRegion("Caribbean",0);

        assertEquals(0, topCitiesInRegion.size());
    }

    @Test
    void getAllCitiesInCountryNormalTest()
    {
        ArrayList<CityReport> citiesInCountry = cityReport.getCitiesInCountry("Belize");
        assertNotNull(citiesInCountry, "Cities list should not be null.");

        CityReport firstCity = citiesInCountry.get(0);
        assertEquals(184, firstCity.getId());
        assertEquals("Belize City", firstCity.getName());
        assertEquals("Belize", firstCity.getCountry());
        assertEquals("Belize City", firstCity.getDistrict());
        assertEquals(55810,firstCity.getPopulation());
    }

    @Test
    void getCitiesInCountryNoneExistentRegionTest() {
        ArrayList<CityReport> citiesInCountry = cityReport.getCitiesInCountry("NoneExistentCountry");

        assertEquals(0, citiesInCountry.size());
    }

    @Test
    void getCitiesInCountryNullRegionTest() {
        ArrayList<CityReport> citiesInCountry = cityReport.getCitiesInCountry(null);

        assertEquals(0, citiesInCountry.size());
    }

    @Test
    void getCitiesInCountryNormalTest() {
        ArrayList<CityReport> topCitiesInCountry = cityReport.getCitiesInCountry("Belize",1);

        assertEquals(1, topCitiesInCountry.size());
    }

    @Test
    void getCitiesInCountryNegativeNumberTest() {
        ArrayList<CityReport> topCitiesInCountry = cityReport.getCitiesInCountry("Belize",-5);

        assertNull(topCitiesInCountry);
    }

    @Test
    void getCitiesInCountryZeroTest() {
        ArrayList<CityReport> topCitiesInCountry = cityReport.getCitiesInCountry("Belize",0);

        assertEquals(0, topCitiesInCountry.size());
    }
}
