package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        if (populations == null || populations.isEmpty())
        {
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
}
