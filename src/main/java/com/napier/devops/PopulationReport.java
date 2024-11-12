package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

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
                    String.format("%-40s %-25s %-25s %15s %25s %15s",
                            population.getName(), population.getPopulation(), population.getUrbanPopulation(), population.getUrbanPopulationPercentage(), population.getRuralPopulation(), population.getRuralPopulationPercentage());
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
    public PopulationReport mapToPopulation(ResultSet rset) throws SQLException {
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
     * Gets the population of the world.
     * @return A PopulationReport object containing the total population of the world.
     */
    public PopulationReport getTotalPopulationOfTheWorld() {
        String query = "SELECT SUM(ctry.Population) AS World_Population FROM country ctry";

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            ResultSet rset = prepStmt.executeQuery();

            PopulationReport locationPopulation = new PopulationReport();

            if (rset.next()) {
                locationPopulation.setPopulation(rset.getLong(1));
            }

            return locationPopulation;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }

    }

    /**
     * Gets the population of a continent, region, country, district or city.
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

    // TODO: - possibly add error handle
    @Override
    public String toString() {
        return String.format("%s - %s",
                getName() != null ? getName():"World",
                NumberFormat.getInstance().format(getPopulation()));
    }
}
