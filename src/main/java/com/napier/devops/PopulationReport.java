package com.napier.devops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Represents a population report in the system.
 * This class stores report information such as name, total population, urban population, rural population and their respective percentages.
 */
public class PopulationReport extends Report {
    private long urbanPopulation;
    private long ruralPopulation;
    private float urbanPopulationPercentage;
    private float ruralPopulationPercentage;

    public long getUrbanPopulation() {
        return urbanPopulation;
    }

    public void setUrbanPopulation(long urbanPopulation) {
        this.urbanPopulation = urbanPopulation;
    }

    public long getRuralPopulation() {
        return ruralPopulation;
    }

    public void setRuralPopulation(long ruralPopulation) {
        this.ruralPopulation = ruralPopulation;
    }

    public float getUrbanPopulationPercentage() {
        return urbanPopulationPercentage;
    }

    public void setUrbanPopulationPercentage(float urbanPopulationPercentage) {
        this.urbanPopulationPercentage = urbanPopulationPercentage;
    }

    public float getRuralPopulationPercentage() {
        return ruralPopulationPercentage;
    }

    public void setRuralPopulationPercentage(float ruralPopulationPercentage) {
        this.ruralPopulationPercentage = ruralPopulationPercentage;
    }

    /**
     *
     * @return PopulationReport object instance as a string.
     */
    @Override
    public String toString() {
        if (getUrbanPopulation() == 0 && getUrbanPopulationPercentage() == 0 && getRuralPopulation() == 0 && getRuralPopulationPercentage() == 0) {
            return String.format("PopulationReport{Name = %s, Total Population = %s}",
                    getName(),
                    NumberFormat.getInstance().format(getPopulation()));
        } else {
            return String.format("PopulationReport{Name = %s, Total Population = %s, Urban Population = %s, Urban Percentage = %.2f%%, Rural Population = %s, Rural Percentage = %.2f%%}",
                    getName() != null ? getName() : "N/A",
                    NumberFormat.getInstance().format(getPopulation()),
                    NumberFormat.getInstance().format(getUrbanPopulation()),
                    getUrbanPopulationPercentage(),
                    NumberFormat.getInstance().format(getRuralPopulation()),
                    getRuralPopulationPercentage());
        }
    }

    /**
     * Prints a list of population data.
     *
     * @param populations The list of population data to print.
     */
    public void displayPopulations(ArrayList<PopulationReport> populations) {
        // Check populations is not null
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data found!");
            return;
        }

        // Print header
        System.out.println(String.format("%-40s %-25s %-25s %15s %25s %15s", "Name", "Total Population", "Urban Population", "Percentage", "Rural Population", "Percentage"));
        // Loop over all population data in the list
        for (PopulationReport population : populations) {
            if (population == null) {
                continue;
            }
            String population_string =
                    String.format("%-40s %-25s %-25s %15s%% %25s %15s%%",
                            population.getName(),
                            NumberFormat.getInstance().format(population.getPopulation()),
                            NumberFormat.getInstance().format(population.getUrbanPopulation()),
                            population.getUrbanPopulationPercentage(),
                            NumberFormat.getInstance().format(population.getRuralPopulation()),
                            population.getRuralPopulationPercentage());
            System.out.println(population_string);
        }
    }

    /**
     * Converts a result set row into a PopulationReport object.
     *
     * @param rset Holds query result set containing population data.
     * @return A PopulationReport instance.
     * @throws SQLException If SQL error occurs while accessing data.
     */
    public PopulationReport mapToPopulationReport(ResultSet rset) throws SQLException {
        PopulationReport population = new PopulationReport();
        population.setName(rset.getString(1));
        population.setPopulation(rset.getLong(2));
        population.setUrbanPopulation(rset.getLong(3));
        population.setUrbanPopulationPercentage(rset.getFloat(4));
        population.setRuralPopulation(rset.getLong(5));
        population.setRuralPopulationPercentage(rset.getFloat(6));

        return population;
    }

    /**
     * Outputs to Markdown
     *
     * @param populations The list of population data to output.
     * @param filename The name of the outputted file.
     */
    public void outputPopulationReports(ArrayList<PopulationReport> populations, String filename) {
        // Check populations is null
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data to output!");
            return;
        }

        if (filename == null) {
            System.out.println("No filename was entered!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Total Population | Urban Population | Urban Percentage | Rural Population | Rural Percentage |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all population data in the list
        for (PopulationReport population : populations) {
            if (population == null) continue;
            sb.append("| " + population.getName() + " | "
                    + NumberFormat.getInstance().format(population.getPopulation()) + " | "
                    + NumberFormat.getInstance().format(population.getUrbanPopulation()) + " | "
                    + population.getUrbanPopulationPercentage() + "%" + " | "
                    + NumberFormat.getInstance().format(population.getRuralPopulation()) + " | "
                    + population.getRuralPopulationPercentage() + "%" + " |\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses method overloading to output single row queries to markdown.
     *
     * @param populationData Holds population data to output for a single PopulationReport object instance.
     * @param filename The name of the file.
     */
    public void outputPopulationReports(PopulationReport populationData, String filename) {
        // Check populations is null
        if (populationData == null) {
            System.out.println("No population data to output!");
            return;
        }

        if (filename == null) {
            System.out.println("No filename was entered!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Total Population |\r\n");
        sb.append("| --- | --- |\r\n");
        sb.append("| " + populationData.getName() + " | "
                + NumberFormat.getInstance().format(populationData.getPopulation()) + " |\r\n");

        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the population data for each continent, including city and non-city populations.
     *
     * @return A list of population data for each continent.
     */
    public ArrayList<PopulationReport> getPopulationDataByContinent() {
        String query = "SELECT ctry.Continent, SUM(DISTINCT(ctry.Population)) AS Total_Population, "
                + "IFNULL(SUM(cty.Population),0) AS Urban_Population, "
                + "IFNULL(ROUND(((IFNULL(SUM(cty.Population),0) * 100) / SUM(DISTINCT(ctry.Population))),2),0) AS Urban_Percentage, "
                + "IFNULL((SUM(DISTINCT(ctry.Population)) - SUM(cty.Population)),0) AS Rural_Population, "
                + "IFNULL(ROUND((((SUM(DISTINCT(ctry.Population)) - IFNULL(SUM(cty.Population),0)) * 100) / SUM(DISTINCT(ctry.Population))),2),0) AS Rural_Percentage "
                + "FROM country ctry "
                + "LEFT JOIN city cty ON ctry.Code = cty.CountryCode "
                + "GROUP BY ctry.Continent "
                + "ORDER BY Total_Population DESC";

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<PopulationReport> populationReports = new ArrayList<>();

            while (rset.next()) {
                populationReports.add(mapToPopulationReport(rset));
            }

            return populationReports;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details.");
            return null;
        }
    }

    /**
     * Retrieves the population data for each region, including city and non-city populations.
     *
     * @return A list of population data for each region.
     */
    public ArrayList<PopulationReport> getPopulationDataByRegion() {
        String query = "SELECT ctry.Region, SUM(DISTINCT(ctry.Population)) AS Total_Population, "
                + "IFNULL(SUM(cty.Population),0) AS Urban_Population, "
                + "IFNULL(ROUND(((IFNULL(SUM(cty.Population),0) * 100) / SUM(DISTINCT(ctry.Population))),2),0) AS Urban_Percentage, "
                + "IFNULL((SUM(DISTINCT(ctry.Population)) - SUM(cty.Population)),0) AS Rural_Population, "
                + "IFNULL(ROUND((((SUM(DISTINCT(ctry.Population)) - IFNULL(SUM(cty.Population),0)) * 100) / SUM(DISTINCT(ctry.Population))),2),0) AS Rural_Percentage "
                + "FROM country ctry "
                + "LEFT JOIN city cty ON ctry.Code = cty.CountryCode "
                + "GROUP BY ctry.Region "
                + "ORDER BY Total_Population DESC";

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<PopulationReport> populationReports = new ArrayList<>();

            while (rset.next()) {
                populationReports.add(mapToPopulationReport(rset));
            }

            return populationReports;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details.");
            return null;
        }
    }

    /**
     * Retrieves the population data for each country, including city and non-city populations.
     *
     * @return A list of population data for each country.
     */
    public ArrayList<PopulationReport> getPopulationDataByCountry() {
        String query = "SELECT co.Name AS Country, "
                + "co.Population AS TotalPopulation, "
                + "SUM(ci.Population) AS CityPopulation, "
                + "ROUND(((SUM(ci.Population) * 100) / co.Population),2) AS CityPercentage, "
                + "(co.Population - SUM(ci.Population)) AS Rural_Population, "
                + "ROUND((((co.Population - SUM(ci.Population)) * 100) / co.Population),2) AS RuralPopulation "
                + "FROM country co "
                + "LEFT JOIN city ci ON co.Code = ci.CountryCode "
                + "GROUP BY co.Code, co.Name ";

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<PopulationReport> populationReports = new ArrayList<>();

            while (rset.next()) {
                populationReports.add(mapToPopulationReport(rset));
            }

            return populationReports;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details.");
            return null;
        }
    }

    /**
     * Gets the population of the world.
     *
     * @return A PopulationReport object containing the total population of the world.
     */
    public PopulationReport getTotalPopulationOfTheWorld() {
        String query = "SELECT SUM(ctry.Population) AS World_Population FROM country ctry";

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            ResultSet rset = prepStmt.executeQuery();

            PopulationReport worldPopulation = new PopulationReport();

            if (rset.next()) {
                worldPopulation.setName("World");
                worldPopulation.setPopulation(rset.getLong(1));
            }

            return worldPopulation;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }

    }

    /**
     * Gets the population of a continent, region, country, district or city.
     *
     * @param level An enum instance that determines the geographical level being queried.
     * @param location The name of the location.
     * @return A PopulationReport object containing the name and total population of a continent, region, country, district or city.
     */
    public PopulationReport getTotalPopulationForLocation(GeographicLevel level, String location) {
        String query = switch (level) {
            case CONTINENT -> "SELECT ctry.Continent, SUM(ctry.Population) AS Continent_Population "
                    + "FROM country ctry "
                    + "WHERE ctry.Continent = ?";
            case REGION -> "SELECT ctry.Region, SUM(ctry.Population) AS Region_Population "
                    + "FROM country ctry "
                    + "WHERE ctry.Region = ?";
            case COUNTRY -> "SELECT ctry.Name, ctry.Population AS Country_Population "
                    + "FROM country ctry "
                    + "WHERE ctry.Name = ?";
            case DISTRICT -> "SELECT cty.District, SUM(cty.Population) AS District_Population "
                    + "FROM city cty "
                    + "WHERE cty.District = ?";
            case CITY -> "SELECT cty.Name, cty.Population AS City_Population "
                    + "FROM city cty "
                    + "WHERE cty.Name = ?";
        };

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, location);

            ResultSet rset = prepStmt.executeQuery();

            PopulationReport locationPopulation = new PopulationReport();

            if (rset.next()) {
                locationPopulation.setName(rset.getString(1));
                locationPopulation.setPopulation(rset.getLong(2));
            }

            return locationPopulation;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }
}
