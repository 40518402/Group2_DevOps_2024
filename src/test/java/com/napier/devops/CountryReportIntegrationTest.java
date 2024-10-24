package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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

    }

    @Test
    void testGetCountriesByContinent()
    {
        ArrayList<CountryReport> countries = countryReport.getCountriesByContinent("Asia", 1);
        assertEquals(countries.get(0).getCode(), "CHN");
        assertEquals(countries.get(0).getName(), "China");
        assertEquals(countries.get(0).getContinent(), "Asia");
        assertEquals(countries.get(0).getRegion(), "Eastern Asia");
        assertEquals(countries.get(0).getPopulation(), 1277558000);
        assertEquals(countries.get(0).getCapital(), "Peking");
    }
}