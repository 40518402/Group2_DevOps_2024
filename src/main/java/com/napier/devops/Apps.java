package com.napier.devops;

import java.util.ArrayList;

/**
 * This class serves as the entry point for the application and generates the various reports
 * for the user.
 */
public class Apps {
    public static void main(String[] args) {
        // Create new PopulationReport Object Instance
        PopulationReport popReport = new PopulationReport();

        // Connect to database
        if(args.length < 1){
            popReport.connect("localhost:33060", 30000);
        }else{
            popReport.connect(args[0], Integer.parseInt(args[1]));
        }

        CountryReport countryReport = new CountryReport();

        // All the countries in the world organised by largest population to smallest.
        ArrayList<CountryReport> allCountriesInWorld = countryReport.getCountriesInWorld();
        countryReport.outputCountryReports(allCountriesInWorld, "allCountriesInWorld.md");
        countryReport.displayCountries(allCountriesInWorld);

        System.out.println();

        // All the countries in a continent organised by largest population to smallest.
        ArrayList<CountryReport> allCountriesInNorthAmerica = countryReport.getCountriesInContinent("North America");
        countryReport.outputCountryReports(allCountriesInNorthAmerica, "allCountriesInNorthAmerica.md");
        countryReport.displayCountries(allCountriesInNorthAmerica);

        System.out.println();

        // All the countries in a region organised by largest population to smallest.
        ArrayList<CountryReport> allCountriesInCaribbean = countryReport.getCountriesInRegion("Caribbean");
        countryReport.outputCountryReports(allCountriesInCaribbean, "allCountriesInRegion.md");
        countryReport.displayCountries(allCountriesInCaribbean);

        System.out.println();

        // The top N populated countries in the world where N is provided by the user.
        ArrayList<CountryReport> topThreeCountriesInWorld = countryReport.getCountriesInWorld(3);
        countryReport.outputCountryReports(topThreeCountriesInWorld, "topThreeCountriesInWorld.md");
        countryReport.displayCountries(topThreeCountriesInWorld);

        System.out.println();

        // The top N populated countries in a continent where N is provided by the user.
        ArrayList<CountryReport> topThreeCountriesInNorthAmerica = countryReport.getCountriesInContinent("North America", 3);
        countryReport.outputCountryReports(topThreeCountriesInNorthAmerica, "topThreeCountriesInNorthAmerica.md");
        countryReport.displayCountries(topThreeCountriesInNorthAmerica);

        System.out.println();

        // The top N populated countries in a region where N is provided by the user.
        ArrayList<CountryReport> topThreeCountriesInCaribbean = countryReport.getCountriesInRegion("Caribbean", 3);
        countryReport.outputCountryReports(topThreeCountriesInCaribbean, "topThreeCountriesInCaribbean.md");
        countryReport.displayCountries(topThreeCountriesInCaribbean);

        System.out.println();

        CityReport cityReport = new CityReport();

        // All the cities in the world organised by largest population to smallest.
        ArrayList<CityReport> allCitiesInWorld = cityReport.getCitiesInWorld();
        cityReport.outputCityReports(allCitiesInWorld, "allCitiesInWorld.md", false);
        cityReport.displayCities(allCitiesInWorld, false);

        System.out.println();

        // All the cities in a continent organised by largest population to smallest.
        ArrayList<CityReport> allCitiesInNorthAmerica = cityReport.getCitiesInContinent("North America");
        cityReport.outputCityReports(allCitiesInNorthAmerica, "allCitiesInNorthAmerica.md", false);
        cityReport.displayCities(allCitiesInNorthAmerica, false);

        System.out.println();

        // All the cities in a region organised by largest population to smallest.
        ArrayList<CityReport> allCitiesInCaribbean = cityReport.getCitiesInRegion("Caribbean");
        cityReport.outputCityReports(allCitiesInCaribbean, "allCitiesInCaribbean.md", false);
        cityReport.displayCities(allCitiesInCaribbean, false);

        System.out.println();

        // All the cities in a country organised by largest population to smallest.
        ArrayList<CityReport> allCitiesInBelize = cityReport.getCitiesInCountry("Belize");
        cityReport.outputCityReports(allCitiesInBelize, "allCitiesInBelize.md", false);
        cityReport.displayCities(allCitiesInBelize, false);

        System.out.println();

        // All the cities in a district organised by largest population to smallest.
        ArrayList<CityReport> allCitiesInOntario = cityReport.getCitiesInDistrict("Ontario");
        cityReport.outputCityReports(allCitiesInOntario, "allCitiesInOntario.md", false);
        cityReport.displayCities(allCitiesInOntario, false);

        System.out.println();

        // The top N populated cities in the world where N is provided by the user.
        ArrayList<CityReport> topThreeCitiesInWorld = cityReport.getCitiesInWorld(3);
        cityReport.outputCityReports(topThreeCitiesInWorld, "topThreeCitiesInWorld.md", false);
        cityReport.displayCities(topThreeCitiesInWorld, false);

        System.out.println();

        // The top N populated cities in a continent where N is provided by the user.
        ArrayList<CityReport> topThreeCitiesInNorthAmerica = cityReport.getCitiesInContinent("North America", 3);
        cityReport.outputCityReports(topThreeCitiesInNorthAmerica, "topThreeCitiesInNorthAmerica.md", false);
        cityReport.displayCities(topThreeCitiesInNorthAmerica, false);

        System.out.println();

        // The top N populated cities in a region where N is provided by the user.
        ArrayList<CityReport> topThreeCitiesInCaribbean = cityReport.getCitiesInRegion("Caribbean", 3);
        cityReport.outputCityReports(topThreeCitiesInCaribbean, "topThreeCitiesInCaribbean.md", false);
        cityReport.displayCities(topThreeCitiesInCaribbean, false);

        System.out.println();

        //The top N populated cities in a country where N is provided by the user.
        ArrayList<CityReport> topThreeCitiesInJapan = cityReport.getCitiesInCountry("Japan", 3);
        cityReport.outputCityReports(topThreeCitiesInJapan, "topThreeCitiesInJapan.md", false);
        cityReport.displayCities(topThreeCitiesInJapan, false);

        System.out.println();

        // The top N populated cities in a district where N is provided by the user.
        ArrayList<CityReport> topThreeCitiesInOntario = cityReport.getCitiesInDistrict("Ontario", 3);
        cityReport.outputCityReports(topThreeCitiesInOntario, "topThreeCitiesInOntario.md", false);
        cityReport.displayCities(topThreeCitiesInOntario, false);

        System.out.println();

        // All the capital cities in the world organised by largest population to smallest.
        ArrayList<CityReport> allCapitalCitiesInWorld = cityReport.getCapitalCitiesWorldWide();
        cityReport.outputCityReports(allCapitalCitiesInWorld, "allCapitalCitiesInWorld.md", true);
        cityReport.displayCities(allCapitalCitiesInWorld, true);

        System.out.println();

        // All the capital cities in a continent organised by largest population to smallest.
        ArrayList<CityReport> allCapitalCitiesInNorthAmerica = cityReport.getCapitalCitiesInContinent("North America");
        cityReport.outputCityReports(allCapitalCitiesInNorthAmerica, "allCapitalCitiesInNorthAmerica.md", true);
        cityReport.displayCities(allCapitalCitiesInNorthAmerica, true);

        System.out.println();

        // All the capital cities in a region organised by largest to smallest.
        ArrayList<CityReport> allCapitalCitiesInCaribbean = cityReport.getCapitalCitiesInRegion("Caribbean");
        cityReport.outputCityReports(allCapitalCitiesInCaribbean, "allCapitalCitiesInCaribbean.md", true);
        cityReport.displayCities(allCapitalCitiesInCaribbean, true);

        System.out.println();

        // The top N populated capital cities in the world where N is provided by the user.
        ArrayList<CityReport> topThreeCapitalCitiesInWorld = cityReport.getCapitalCitiesWorldWide(3);
        cityReport.outputCityReports(topThreeCapitalCitiesInWorld, "topThreeCapitalCitiesInWorld.md", true);
        cityReport.displayCities(topThreeCapitalCitiesInWorld, true);

        System.out.println();

        // The top N populated capital cities in a continent where N is provided by the user.
        ArrayList<CityReport> topThreeCapitalCitiesInNorthAmerica = cityReport.getCapitalCitiesInContinent("North America", 3);
        cityReport.outputCityReports(topThreeCapitalCitiesInNorthAmerica, "topThreeCapitalCitiesInNorthAmerica.md", true);
        cityReport.displayCities(topThreeCapitalCitiesInNorthAmerica, true);

        System.out.println();

        // The top N populated capital cities in a region where N is provided by the user.
        ArrayList<CityReport> topThreeCapitalCitiesInCaribbean = cityReport.getCapitalCitiesInRegion("Caribbean", 3);
        cityReport.outputCityReports(topThreeCapitalCitiesInCaribbean, "topThreeCapitalCitiesInCaribbean.md", true);
        cityReport.displayCities(topThreeCapitalCitiesInCaribbean, true);

        System.out.println();

        // The population of people, people living in cities, and people not living in cities in each continent.
        ArrayList<PopulationReport> eachContinentPopulation = popReport.getPopulationDataByContinent();
        popReport.outputPopulationReports(eachContinentPopulation, "eachContinentPopulation.md");
        popReport.displayPopulations(eachContinentPopulation);

        System.out.println();

        // The population of people, people living in cities, and people not living in cities in each region.
        ArrayList<PopulationReport> eachRegionPopulation = popReport.getPopulationDataByRegion();
        popReport.outputPopulationReports(eachRegionPopulation, "eachRegionPopulation.md");
        popReport.displayPopulations(eachRegionPopulation);

        System.out.println();

        // The population of people, people living in cities, and people not living in cities in each country.
        ArrayList<PopulationReport> eachCountryPopulation = popReport.getPopulationDataByCountry();
        popReport.outputPopulationReports(eachCountryPopulation, "eachCountryPopulation.md");
        popReport.displayPopulations(eachCountryPopulation);

        System.out.println();

        // The population of the world.
        PopulationReport worldPopulation = popReport.getTotalPopulationOfTheWorld();
        popReport.outputPopulationReports(worldPopulation, "worldPopulation.md");
        System.out.println(worldPopulation.toString());

        System.out.println();

        // The population of a continent.
        PopulationReport northAmericaPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.CONTINENT, "North America");
        popReport.outputPopulationReports(northAmericaPopulation, "northAmericaPopulation.md");
        System.out.println(northAmericaPopulation.toString());

        System.out.println();

        // The population of a region.
        PopulationReport caribbeanPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.REGION, "Caribbean");
        popReport.outputPopulationReports(caribbeanPopulation, "caribbeanPopulation.md");
        System.out.println(caribbeanPopulation.toString());

        System.out.println();

        // The population of a country.
        PopulationReport belizePopulation = popReport.getTotalPopulationForLocation(GeographicLevel.COUNTRY, "Belize");
        popReport.outputPopulationReports(belizePopulation, "belizePopulation.md");
        System.out.println(belizePopulation.toString());

        System.out.println();

        // The population of a district.
        PopulationReport ontarioPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.DISTRICT, "Ontario");
        popReport.outputPopulationReports(ontarioPopulation, "ontarioPopulation.md");
        System.out.println(ontarioPopulation.toString());

        System.out.println();

        // The population of a city.
        PopulationReport belmopanPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.CITY, "Belmopan");
        popReport.outputPopulationReports(belmopanPopulation, "belmopanPopulation.md");
        System.out.println(belmopanPopulation.toString());

        System.out.println();

        // The number of people who speak the following languages from the greatest number to smallest, including the percentage of the world population.
        LanguageReport languageReport = new LanguageReport();

        ArrayList<LanguageReport> languageData = languageReport.getLanguageSpeakersData();
        languageReport.outputLanguageReports(languageData, "languageData.md");
        languageReport.displayLanguages(languageData);

        // Disconnect from database
        popReport.disconnect();
    }
}