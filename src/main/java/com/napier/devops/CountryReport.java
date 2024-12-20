package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Represents a country report in the system.
 * This class stores report information such as code, name, continent, region, population and capital.
 */
public class CountryReport extends Report {
    private String code;
    private String continent;
    private String region;
    private String capital;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     *
     * @return CountryReport object instance as a string.
     */
    @Override
    public String toString() {
        return String.format("CountryReport{Code = %s, Name = %s, Continent = %s, Region = %s, Population = %s, Capital = %s}",
                getCode() != null ? getCode() : "N/A",
                getName() != null ? getName() : "N/A",
                getContinent() != null ? getContinent() : "N/A",
                getRegion() != null ? getRegion() : "N/A",
                NumberFormat.getInstance().format(getPopulation()),
                getCapital() != null ? getCapital() : "N/A");
    }

    /**
     * Prints a list of countries.
     *
     * @param countries The list of countries to print.
     */
    public void displayCountries(ArrayList<CountryReport> countries) {
        // Check countries is not null
        if (countries == null || countries.isEmpty())
        {
            System.out.println("No countries found!");
            return;
        }

        // Print header
        System.out.printf("%-5s %-48s %-15s %-28s %20s %10s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        // Loop over all countries in the list
        for (CountryReport country : countries) {
            if (country == null) {
                continue;
            }
            String country_string =
                    String.format("%-5s %-48s %-15s %-28s %20s %10s",
                            country.getCode(), country.getName(), country.getContinent(), country.getRegion(), NumberFormat.getInstance().format(country.getPopulation()), country.getCapital() != null ? country.getCapital() : "N/A");
            System.out.println(country_string);
        }
    }

    /**
     * Outputs to Markdown
     *
     * @param countries The list of countries to output.
     * @param filename The name of the outputted file.
     */
    public void outputCountryReports(ArrayList<CountryReport> countries, String filename) {
        // Check countries is null
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries to output!");
            return;
        }

        if (filename == null) {
            System.out.println("No filename was entered!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Code | Country | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all countries in the list
        for (CountryReport country : countries) {
            if (country == null) continue;
            sb.append("| ").append(country.getCode()).append(" | ").append(country.getName()).append(" | ").append(country.getContinent()).append(" | ").append(country.getRegion()).append(" | ").append(NumberFormat.getInstance().format(country.getPopulation())).append(" | ").append(country.getCapital()).append(" |\r\n");
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
     * Converts a result set row into a CountryReport object.
     *
     * @param rset Holds query result set containing country data.
     * @return A CountryReport instance.
     * @throws SQLException If SQL error occurs while accessing data.
     */
    public CountryReport mapToCountry(ResultSet rset) throws SQLException {
        CountryReport country = new CountryReport();
        country.setCode(rset.getString("ctry.Code"));
        country.setName(rset.getString("ctry.Name"));
        country.setContinent(rset.getString("ctry.Continent"));
        country.setRegion(rset.getString("ctry.Region"));
        country.setPopulation(rset.getLong("ctry.Population"));
        country.setCapital(rset.getString("Capital"));

        return country;
    }

    /**
     * Gets all the countries in the world, organized by population (largest to smallest), or the top N populated countries if N is not null.
     *
     * @param N The number of top populated countries to retrieve.
     * @return A list of all countries in the world, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesInWorld(Integer N) {
        // SQL query to get all countries in the world, ordered by population
        String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                + "FROM country ctry "
                + "LEFT JOIN city cty ON ctry.Capital = cty.ID "
                + "ORDER BY ctry.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement
        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {

            if (N != null) {
                prepStmt.setInt(1, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CountryReport> countries = new ArrayList<>();

            // Loop through the result set and create CountryReport objects
            while (rset.next()) {
                countries.add(mapToCountry(rset));
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve country details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CountryReport> getCountriesInWorld() {
        return getCountriesInWorld(null);
    }

    /**
     * Retrieves all the countries in a continent in descending order, or the top N populated countries if N is not null.
     *
     * @param continent The continent for which the top populated countries will be retrieved.
     * @param N The number of top populated countries to retrieve.
     * @return A list of countries in a continent, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesInContinent(String continent, Integer N) {
        String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name As Capital "
                + "FROM country ctry "
                + "LEFT JOIN city cty ON ctry.Capital = cty.ID "
                + "WHERE Continent = ? "
                + "ORDER BY Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, continent);

            if (N != null) {
                prepStmt.setInt(2, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CountryReport> countries = new ArrayList<>();

            while (rset.next()) {
                countries.add(mapToCountry(rset));
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CountryReport> getCountriesInContinent(String continent) {
        return getCountriesInContinent(continent, null);
    }

    /**
     * Gets all the countries in a region, organized by population (largest to smallest), or the top N populated countries if N is not null.
     * @param region The region for which the top populated countries will be retrieved.
     * @param N      The number of top populated countries to retrieve.
     *
     * @return A list of all countries in a region, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesInRegion(String region, Integer N) {
        // SQL query to get countries in a region, ordered by population
        String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                + "FROM country ctry "
                + "LEFT JOIN city cty ON ctry.Capital = cty.ID "
                + "WHERE ctry.Region = ? "
                + "ORDER BY ctry.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement with the region parameter
        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, region);

            if (N != null) {
                prepStmt.setInt(2, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CountryReport> countries = new ArrayList<>();

            // Loop through the result set and create CountryReport objects
            while (rset.next()) {
                countries.add(mapToCountry(rset));
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CountryReport> getCountriesInRegion(String region) {
        return getCountriesInRegion(region, null);
    }
}



