package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CityReportUnitTest {
    static CityReport cityReport;

    @BeforeAll
    static void init() {

        cityReport = new CityReport();
    }

    @Test
    void cityGettersTest() {
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 185);
        belmopan.setName("Belmopan");
        belmopan.setCountry("Belize");
        belmopan.setDistrict("Cayo");
        belmopan.setPopulation(7105);

        assertEquals(185, belmopan.getId());
        assertEquals("Belmopan", belmopan.getName());
        assertEquals("Belize", belmopan.getCountry());
        assertEquals("Cayo", belmopan.getDistrict());
        assertEquals(7105, belmopan.getPopulation());
    }

    @Test
    void toStringCityTestEmpty() {
        CityReport belmopan = new CityReport();
        System.out.println(belmopan.toString());
    }

    @Test
    void toStringCityTestContainsNullValue() {
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 0);
        belmopan.setName(null);
        belmopan.setCountry(null);
        belmopan.setDistrict(null);
        belmopan.setPopulation(0);

        System.out.println(belmopan.toString());
    }

    @Test
    void toStringCityTestNormal() {
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 185);
        belmopan.setName("Belmopan");
        belmopan.setCountry("Belize");
        belmopan.setDistrict("Cayo");
        belmopan.setPopulation(7105);

        System.out.println(belmopan.toString());
    }

    @Test
    void displayCitiesTestNull() {
        cityReport.displayCities(null, false);
    }

    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cityReport.displayCities(cities, false);
    }

    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cities.add(null);
        cityReport.displayCities(cities, false);
    }

    @Test
    void displayCitiesTestNormal()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 185);
        belmopan.setName("Belmopan");
        belmopan.setCountry("Belize");
        belmopan.setDistrict("Cayo");
        belmopan.setPopulation(7105);
        cities.add(belmopan);
        cityReport.displayCities(cities, false);
    }

    @Test
    void displayCapitalCitiesTestNull() {
        cityReport.displayCities(null, true);
    }

    @Test
    void displayCapitalCitiesTestEmpty()
    {
        ArrayList<CityReport> capitalCities = new ArrayList<CityReport>();
        cityReport.displayCities(capitalCities, true);
    }

    @Test
    void displayCapitalCitiesTestContainsNull()
    {
        ArrayList<CityReport> capitalCities = new ArrayList<CityReport>();
        capitalCities.add(null);
        cityReport.displayCities(capitalCities, true);
    }

    @Test
    void displayCapitalCitiesTestNormal()
    {
        ArrayList<CityReport> capitalCities = new ArrayList<CityReport>();
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 185);
        belmopan.setName("Belmopan");
        belmopan.setCountry("Belize");
        belmopan.setDistrict("Cayo");
        belmopan.setPopulation(7105);
        capitalCities.add(belmopan);
        cityReport.displayCities(capitalCities, true);
    }
}
