package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PopulationReportUnitTest {
    static PopulationReport populationReport;

    @BeforeAll
    static void init() {
        populationReport = new PopulationReport();
    }

    @Test
    void populationGettersTest() {
        PopulationReport population = new PopulationReport();
        population.setName("Belize");
        population.setPopulation(241000);
        population.setUrbanPopulation(62915);
        population.setUrbanPopulationPercentage(26.11f);
        population.setRuralPopulation(178085);
        population.setRuralPopulationPercentage(73.89f);

        assertEquals("Belize", population.getName());
        assertEquals(241000, population.getPopulation());
        assertEquals(62915, population.getUrbanPopulation());
        assertEquals(26.11f, population.getUrbanPopulationPercentage());
        assertEquals(178085, population.getRuralPopulation());
        assertEquals(73.89f, population.getRuralPopulationPercentage());
    }

    @Test
    void toStringPopulationTestEmpty() {
        PopulationReport population = new PopulationReport();
        System.out.println(population.toString());
    }

    @Test
    void toStringPopulationTestContainsNullValue() {
        PopulationReport population = new PopulationReport();
        population.setName(null);
        population.setPopulation(0);
        population.setUrbanPopulation(0);
        population.setUrbanPopulationPercentage(0);
        population.setRuralPopulation(0);
        population.setRuralPopulationPercentage(0);

        System.out.println(population.toString());
    }

    @Test
    void toStringPopulationTestNormal() {
        PopulationReport population = new PopulationReport();
        population.setName("Belize");
        population.setPopulation(241000);
        population.setUrbanPopulation(62915);
        population.setUrbanPopulationPercentage(26.11f);
        population.setRuralPopulation(178085);
        population.setRuralPopulationPercentage(73.89f);

        System.out.println(population.toString());
    }

    @Test
    void displayPopulationsTestNull() {
        populationReport.displayPopulations(null);
    }

    @Test
    void displayPopulationsTestEmpty() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        populationReport.displayPopulations(populations);
    }

    @Test
    void displayPopulationsTestContainsNull() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        populations.add(null);
        populationReport.displayPopulations(populations);
    }

    @Test
    void displayPopulationsTestNormal() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        PopulationReport population = new PopulationReport();
        population.setName("Belize");
        population.setPopulation(241000);
        population.setUrbanPopulation(62915);
        population.setUrbanPopulationPercentage(26.11f);
        population.setRuralPopulation(178085);
        population.setRuralPopulationPercentage(73.89f);
        populations.add(population);

        populationReport.displayPopulations(populations);
    }

    @Test
    void outputPopulationReportsNullTest() {
        ArrayList<PopulationReport> populations = null;
        populationReport.outputPopulationReports(populations, "populationsTestNull.md");
    }

    @Test
    void outputPopulationsReportsEmptyTest() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        populationReport.outputPopulationReports(populations, "populationsTestEmpty.md");
    }

    @Test
    void outputPopulationReportsContainsNullTest() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        populations.add(null);

        populationReport.outputPopulationReports(populations, "populationsTestContainsNull.md");
    }

    @Test
    void outputPopulationReportsNormalTest() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        PopulationReport population = new PopulationReport();
        population.setName("Belize");
        population.setPopulation(241000);
        population.setUrbanPopulation(62915);
        population.setUrbanPopulationPercentage(26.11f);
        population.setRuralPopulation(178085);
        population.setRuralPopulationPercentage(73.89f);
        populations.add(population);

        populationReport.outputPopulationReports(populations, "populationsTestNormal.md");
    }

    @Test
    void outputPopulationReportsFilenameNullTest() {
        ArrayList<PopulationReport> populations = new ArrayList<PopulationReport>();
        PopulationReport population = new PopulationReport();
        population.setName("Belize");
        population.setPopulation(241000);
        population.setUrbanPopulation(62915);
        population.setUrbanPopulationPercentage(26.11f);
        population.setRuralPopulation(178085);
        population.setRuralPopulationPercentage(73.89f);
        populations.add(population);

        populationReport.outputPopulationReports(populations, null);
    }

    @Test
    void outputPopulationReportsNullMOTest() {
        PopulationReport populationBelize = null;
        populationReport.outputPopulationReports(populationBelize, "populationBelizeTestNull.md");
    }

    @Test
    void outputPopulationsReportsEmptyMOTest() {
        PopulationReport populationBelize = new PopulationReport();
        populationReport.outputPopulationReports(populationBelize, "populationBelizeTestEmpty.md");
    }

    @Test
    void outputPopulationReportsNormalMOTest() {
        PopulationReport populationBelize = new PopulationReport();
        populationBelize.setName("Belize");
        populationBelize.setPopulation(241000);
        populationBelize.setUrbanPopulation(62915);
        populationBelize.setUrbanPopulationPercentage(26.11f);
        populationBelize.setRuralPopulation(178085);
        populationBelize.setRuralPopulationPercentage(73.89f);

        populationReport.outputPopulationReports(populationBelize, "populationBelizeTestNormal.md");
    }

    @Test
    void outputPopulationReportsFilenameNullMOTest() {
        PopulationReport populationBelize = new PopulationReport();
        populationBelize.setName("Belize");
        populationBelize.setPopulation(241000);
        populationBelize.setUrbanPopulation(62915);
        populationBelize.setUrbanPopulationPercentage(26.11f);
        populationBelize.setRuralPopulation(178085);
        populationBelize.setRuralPopulationPercentage(73.89f);

        populationReport.outputPopulationReports(populationBelize, null);
    }
}
