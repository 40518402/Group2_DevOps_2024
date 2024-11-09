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
        ArrayList<CityReport> citiesInContinent = cityReport.getCitiesInContinent("Asia",0);

        assertEquals(0, citiesInContinent.size());
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
    void getCitiesInCountryNoneExistentCountryTest() {
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

    @Test
    void getAllCitiesInDistrictNormalTest()
    {
        ArrayList<CityReport> citiesInDistrict = cityReport.getCitiesInDistrict("Ontario");
        assertNotNull(citiesInDistrict, "Cities list should not be null.");

        CityReport firstCity = citiesInDistrict.get(0);
        assertEquals(1812, firstCity.getId());
        assertEquals("Toronto", firstCity.getName());
        assertEquals("Canada", firstCity.getCountry());
        assertEquals("Ontario", firstCity.getDistrict());
        assertEquals(688275,firstCity.getPopulation());
    }

    @Test
    void getCitiesInDistrictNoneExistentDistrictTest() {
        ArrayList<CityReport> citiesInDistrict = cityReport.getCitiesInDistrict("NoneExistentDistrict");

        assertEquals(0, citiesInDistrict.size());
    }

    @Test
    void getCitiesInDistrictNullRegionTest() {
        ArrayList<CityReport> citiesInDistrict = cityReport.getCitiesInDistrict(null);

        assertEquals(0, citiesInDistrict.size());
    }

    @Test
    void getCitiesInDistrictNormalTest() {
        ArrayList<CityReport> topCitiesInDistrict = cityReport.getCitiesInDistrict("Ontario",5);

        assertEquals(5, topCitiesInDistrict.size());
    }

    @Test
    void getCitiesInDistrictNegativeNumberTest() {
        ArrayList<CityReport> topCitiesInDistrict = cityReport.getCitiesInDistrict("District",-5);

        assertNull(topCitiesInDistrict);
    }

    @Test
    void getCitiesInDistrictZeroTest() {
        ArrayList<CityReport> topCitiesInDistrict = cityReport.getCitiesInDistrict("Ontario",0);

        assertEquals(0, topCitiesInDistrict.size());
    }

    @Test
    void getAllCapitalCitiesInWorldNormalTest() {
        ArrayList<CityReport> capitalCitiesInWorld = cityReport.getCapitalCitiesWorldWide();
        assertNotNull(capitalCitiesInWorld, "Cities list should not be null.");

        CityReport firstCity = capitalCitiesInWorld.get(0);
        assertEquals(2331, firstCity.getId());
        assertEquals("Seoul", firstCity.getName());
        assertEquals("South Korea", firstCity.getCountry());
        assertEquals("Seoul", firstCity.getDistrict());
        assertEquals(9981619, firstCity.getPopulation());
    }

    @Test
    void getCapitalCitiesInWorldNormalTest() {
        ArrayList<CityReport> topCapitalCitiesInWorld = cityReport.getCapitalCitiesWorldwide(10);

        assertEquals(10, topCapitalCitiesInWorld.size());
    }

    @Test
    void getCapitalCitiesInWorldNullNumberTest() {
        ArrayList<CityReport> capitalCitiesInWorld = cityReport.getCapitalCitiesWorldwide(null);

        assertNotNull(capitalCitiesInWorld);
    }

    @Test
    void getCapitalCitiesInWorldNegativeNumberTest() {
        ArrayList<CityReport> capitalCitiesInWorld = cityReport.getCapitalCitiesWorldwide(-5);

        assertNull(capitalCitiesInWorld);
    }

    @Test
    void getCapitalCitiesInWorldZeroTest() {
        ArrayList<CityReport> capitalCitiesInWorld = cityReport.getCapitalCitiesWorldwide(0);

        assertEquals(0, capitalCitiesInWorld.size());
    }

    @Test
    void getAllCapitalCitiesInContinentNormalTest()
    {
        ArrayList<CityReport> capitalCitiesInContinent = cityReport.getCapitalCitiesInContinent("Asia");
        assertNotNull(capitalCitiesInContinent, "Cities list should not be null.");

        CityReport firstCity = capitalCitiesInContinent.get(0);
        assertEquals(2331, firstCity.getId());
        assertEquals("Seoul", firstCity.getName());
        assertEquals("South Korea", firstCity.getCountry());
        assertEquals("Seoul", firstCity.getDistrict());
        assertEquals(9981619, firstCity.getPopulation());
    }

    @Test
    void getCapitalCitiesInContinentNoneExistentContinentTest() {
        ArrayList<CityReport> capitalCitiesInContinent = cityReport.getCapitalCitiesInContinent("NoneExistentContinent");

        assertEquals(0, capitalCitiesInContinent.size());
    }

    @Test
    void getCapitalCitiesInContinentNullContinentTest() {
        ArrayList<CityReport> capitalCitiesInContinent = cityReport.getCapitalCitiesInContinent(null);

        assertEquals(0, capitalCitiesInContinent.size());
    }

    @Test
    void getCapitalCitiesInContinentNormalTest() {
        ArrayList<CityReport> topCapitalCitiesInContinent = cityReport.getCapitalCitiesInContinent("Asia",10);

        assertEquals(10, topCapitalCitiesInContinent.size());
    }

    @Test
    void getCapitalCitiesInContinentNegativeNumberTest() {
        ArrayList<CityReport> topCapitalCitiesInContinent = cityReport.getCapitalCitiesInContinent("Asia",-5);

        assertNull(topCapitalCitiesInContinent);
    }

    @Test
    void getCapitalCitiesInContinentZeroTest() {
        ArrayList<CityReport> capitalCitiesInContinent = cityReport.getCapitalCitiesInContinent("Asia",0);

        assertEquals(0, capitalCitiesInContinent.size());
    }

    @Test
    void getAllCapitalCitiesRegionNormalTest()
    {
        ArrayList<CityReport> capitalCitiesInRegion = cityReport.getCapitalCitiesInRegion("Caribbean");
        assertNotNull(capitalCitiesInRegion, "Cities list should not be null.");

        CityReport firstCity = capitalCitiesInRegion.get(0);
        assertEquals(2413,firstCity.getId());
        assertEquals("La Habana", firstCity.getName());
        assertEquals("Cuba", firstCity.getCountry());
        assertEquals("La Habana", firstCity.getDistrict());
        assertEquals(2256000,firstCity.getPopulation());
    }

    @Test
    void getCapitalCitiesInRegionNoneExistentRegionTest() {
        ArrayList<CityReport> capitalCitiesInRegion = cityReport.getCapitalCitiesInRegion("NoneExistentRegion");

        assertEquals(0, capitalCitiesInRegion.size());
    }

    @Test
    void getCapitalCitiesInRegionNullRegionTest() {
        ArrayList<CityReport> capitalCitiesInRegion = cityReport.getCapitalCitiesInRegion(null);

        assertEquals(0, capitalCitiesInRegion.size());
    }

    @Test
    void getCapitalCitiesInRegionNormalTest() {
        ArrayList<CityReport> topCapitalCitiesInRegion = cityReport.getCapitalCitiesInRegion("Caribbean",5);

        assertEquals(5, topCapitalCitiesInRegion.size());
    }

    @Test
    void getCapitalCitiesInRegionNegativeNumberTest() {
        ArrayList<CityReport> topCapitalCitiesInRegion = cityReport.getCapitalCitiesInRegion("Caribbean",-5);

        assertNull(topCapitalCitiesInRegion);
    }

    @Test
    void getCapitalCitiesInRegionZeroTest() {
        ArrayList<CityReport> topCapitalCitiesInRegion = cityReport.getCapitalCitiesInRegion("Caribbean",0);

        assertEquals(0, topCapitalCitiesInRegion.size());
    }
}
