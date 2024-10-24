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
        ArrayList<CountryReport> countries = countryReport.getCountriesByContinent("Asia", 5);

        CountryReport firstCountry = countries.get(0);

        assertEquals("CHN", firstCountry.getCode());
        assertEquals("China", firstCountry.getName());
        assertEquals("Asia", firstCountry.getContinent());
        assertEquals("Eastern Asia", firstCountry.getRegion());
        assertEquals(1277558000, firstCountry.getPopulation());
        assertEquals("Peking", firstCountry.getCapital());
    }
}