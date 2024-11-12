package com.napier.devops;

/**
 * This class serves as the entry point for the application and generates the various reports
 * for the user.
 */
public class Apps
{
    public static void main(String[] args)
    {
        // Create new CityReport Object Instance
        PopulationReport popReport = new PopulationReport();

        // Connect to database
        if(args.length < 1){
            popReport.connect("localhost:33060", 30000);
        }else{
            popReport.connect(args[0], Integer.parseInt(args[1]));
        }

        // The population of the world.
        PopulationReport worldPopulation = popReport.getTotalPopulationOfTheWorld();
        System.out.println(worldPopulation.toString());

        // The population of a continent.
        PopulationReport continentPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.CONTINENT, "North America");
        System.out.println(continentPopulation.toString());

        // The population of a region.
        PopulationReport regionPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.REGION, "Caribbean");
        System.out.println(regionPopulation.toString());

        // The population of a country.
        PopulationReport countryPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.COUNTRY, "Belize");
        System.out.println(countryPopulation.toString());

        // The population of a district.
        PopulationReport districtPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.DISTRICT, "Ontario");
        System.out.println(districtPopulation.toString());

        // The population of a city.
        PopulationReport cityPopulation = popReport.getTotalPopulationForLocation(GeographicLevel.CITY, "Belmopan");
        System.out.println(cityPopulation.toString());

        // Disconnect from database
        popReport.disconnect();
    }
}