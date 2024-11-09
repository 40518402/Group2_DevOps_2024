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
    void displayCitiesTestNull() {
        cityReport.displayCities(null);
    }

    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cityReport.displayCities(cities);
    }

    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cities.add(null);
        cityReport.displayCities(cities);
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
        cityReport.displayCities(cities);
    }

    @Test
    void displayCapitalCitiesTestNull() {
        cityReport.displayCapitalCities(null);
    }

    @Test
    void displayCapitalCitiesTestEmpty()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cityReport.displayCapitalCities(cities);
    }

    @Test
    void displayCapitalCitiesTestContainsNull()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        cities.add(null);
        cityReport.displayCapitalCities(cities);
    }

    @Test
    void displayCapitalCitiesTestNormal()
    {
        ArrayList<CityReport> cities = new ArrayList<CityReport>();
        CityReport belmopan = new CityReport();
        belmopan.setId((short) 185);
        belmopan.setName("Belmopan");
        belmopan.setCountry("Belize");
        belmopan.setDistrict("Cayo");
        belmopan.setPopulation(7105);
        cities.add(belmopan);
        cityReport.displayCapitalCities(cities);
    }
}
