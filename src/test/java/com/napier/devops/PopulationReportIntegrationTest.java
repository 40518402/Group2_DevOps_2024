package com.napier.devops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationReportIntegrationTest {
    static PopulationReport populationReport;

    @BeforeAll
    static void init() {
        populationReport = new PopulationReport();
        populationReport.connect("localhost:33060", 30000);

        assertNotNull(populationReport.getDatabaseConnection());
    }

    @AfterAll
    static void close() {
        populationReport.disconnect();
    }

    @Test
    void getPopulationDataByContinentNormalTest() {
        ArrayList<PopulationReport> continents = populationReport.getPopulationDataByContinent();
        assertNotNull(continents, "Population data for continents should not be null.");
        PopulationReport firstContinent = continents.get(0);
        assertTrue(firstContinent.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getPopulationDataByRegionNormalTest() {
        ArrayList<PopulationReport> regions = populationReport.getPopulationDataByRegion();
        assertNotNull(regions, "Regions list should not be null.");
        PopulationReport firstRegion = regions.get(0);
        assertTrue(firstRegion.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getPopulationDataByCountryNormalTest() {
        ArrayList<PopulationReport> countries = populationReport.getPopulationDataByCountry();
        assertNotNull(countries, "Countries list should not be null.");
        PopulationReport firstCountry = countries.get(0);
        assertTrue(firstCountry.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationOfTheWorldTest() {
        PopulationReport worldPopulation = populationReport.getTotalPopulationOfTheWorld();
        assertNotNull(worldPopulation, "World population report should not be null.");
        assertTrue(worldPopulation.getPopulation() > 0, "World population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForContinentTest() {
        PopulationReport continentPopulation = populationReport.getTotalPopulationForLocation(GeographicLevel.CONTINENT, "North America");
        assertNotNull(continentPopulation, "North America population report should not be null.");
        assertTrue(continentPopulation.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForNonExistentContinentTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.CONTINENT, "NonExistentContinent");
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a non-existent continent.");
    }

    @Test
    void getTotalPopulationForNullContinentTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.CONTINENT, null);
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a null continent.");
    }

    @Test
    void getTotalPopulationForRegionTest() {
        PopulationReport regionPopulation = populationReport.getTotalPopulationForLocation(GeographicLevel.REGION, "Caribbean");
        assertNotNull(regionPopulation, "Caribbean population report should not be null.");
        assertTrue(regionPopulation.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForNonExistentRegionTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.REGION, "NonExistentRegion");
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a non-existent region.");
    }

    @Test
    void getTotalPopulationForNullRegionTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.REGION, null);
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a null region.");
    }

    @Test
    void getTotalPopulationForCountryTest() {
        PopulationReport countryPopulation = populationReport.getTotalPopulationForLocation(GeographicLevel.COUNTRY, "Belize");
        assertNotNull(countryPopulation, "Belize population report should not be null.");
        assertTrue(countryPopulation.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForNonExistentCountryTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.COUNTRY, "NonExistentRegion");
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a non-existent Country.");
    }

    @Test
    void getTotalPopulationForNullCountryTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.COUNTRY, null);
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a null Country.");
    }

    @Test
    void getTotalPopulationForDistrictTest() {
        PopulationReport districtPopulation = populationReport.getTotalPopulationForLocation(GeographicLevel.DISTRICT, "Ontario");
        assertNotNull(districtPopulation, "Ontario population report should not be null.");
        assertTrue(districtPopulation.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForNonExistentDistrictTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.DISTRICT, "NonExistentRegion");
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a non-existent district.");
    }

    @Test
    void getTotalPopulationForNullDistrictTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.DISTRICT, null);
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a null district.");
    }

    @Test
    void getTotalPopulationForCityTest() {
        PopulationReport cityPopulation = populationReport.getTotalPopulationForLocation(GeographicLevel.CITY, "Belmopan");
        assertNotNull(cityPopulation, "Belmopan population report should not be null.");
        assertTrue(cityPopulation.getPopulation() > 0, "Population should be greater than 0.");
    }

    @Test
    void getTotalPopulationForNonExistentCityTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.CITY, "NonExistentRegion");
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a non-existent city.");
    }

    @Test
    void getTotalPopulationForNullCityTest() {
        PopulationReport population = populationReport.getTotalPopulationForLocation(GeographicLevel.CITY, null);
        assertNotNull(population, "Population report should not be null.");
        assertEquals(0, population.getPopulation(), "Population should be 0 for a null city.");
    }
}
