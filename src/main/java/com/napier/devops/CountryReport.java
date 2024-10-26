package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a country report in the system
 * This class stores report information such as code, name, continent, region, population and capital
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
     * Prints a list of countries.
     *
     * @param countries The list of countries to print.
     */
    public void displayCountries(ArrayList<CountryReport> countries) {
        // Check countries is not null
        if (countries == null)
        {
            System.out.println("No countries found!");
            return;
        }

        // Print header
        System.out.println(String.format("%-5s %-48s %-15s %-28s %20s %10s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (CountryReport country : countries) {
            if (country == null) {
                continue;
            }
            String country_string =
                    String.format("%-5s %-48s %-15s %-28s %20s %10s",
                            country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital() != null ? country.getCapital() : "N/A");
            System.out.println(country_string);
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
     * Gets all the countries in the world, organized by population (largest to smallest).
     *
     * @return A list of all countries in the world, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesByWorldPopulation() {
        try {
            // SQL query to get all countries in the world, ordered by population
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                    + "FROM country ctry, city cty "
                    + "WHERE ctry.Code = cty.CountryCode "
                    + "AND ctry.Capital = cty.ID "
                    + "ORDER BY ctry.Population DESC";

            // Prepare the SQL statement
            PreparedStatement prepStmt = getConnection().prepareStatement(query);

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

    /**
     * Get the top N populated countries in the world where N is provided by the user.
     *
     * @param N         The number of countries to retrieve.
     * @return A list of N populated countries in a World, where N is Provided by the user.
     */
    public ArrayList<CountryReport> getCountriesInWorld(int N) {
        try {
            // SQL query to get countries in the world Populated by user;
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                    + "FROM country ctry, city cty "
                    + "WHERE ctry.Code = cty.CountryCode "
                    + "AND ctry.Capital = cty.ID "
                    + "ORDER BY ctry.Population DESC "
                    + "LIMIT ?";

            // Prepare the SQL statement with the region parameter
            PreparedStatement prepStmt = getConnection().prepareStatement(query);
            prepStmt.setInt(1, N);

            //Execute Query
            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CountryReport> countries = new ArrayList<>();

            // Process the result set
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

    /**
     * Retrieves all the countries in a continent in descending order, or the top N populated countries if N is not null.
     *
     * @param continent The continent for which the top populated countries will be retrieved.
     * @param N         The number of top populated countries to retrieve.
     * @return A list of all top N populated countries in a continent, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesByContinent(String continent, Integer N) {
        try {
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name As Capital "
                    + "FROM country ctry "
                    + "LEFT JOIN city cty ON ctry.Capital = cty.ID "
                    + "WHERE Continent = ? "
                    + "ORDER BY Population DESC";

            if (N != null) {
                query += " LIMIT ?";
            }

            PreparedStatement prepStmt = getConnection().prepareStatement(query);
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
    public ArrayList<CountryReport> getCountriesByContinent(String continent) {
        return getCountriesByContinent(continent, null);
    }

    /**
     * Gets all the countries in a region, organized by population (largest to smallest).
     *
     * @return A list of all countries in a region, or null if there is an error.
     */
    public ArrayList<CountryReport> getCountriesByRegion(String region) {
        try {
            // SQL query to get countries in a region, ordered by population
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                    + "FROM country ctry, city cty "
                    + "WHERE ctry.Code = cty.CountryCode "
                    + "AND ctry.Capital = cty.ID "
                    + "AND ctry.Region = ? "
                    + "ORDER BY ctry.Population DESC";

            // Prepare the SQL statement with the region parameter
            PreparedStatement prepStmt = getConnection().prepareStatement(query);
            prepStmt.setString(1, region);

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

    /**
     * Gets the top N populated countries in a region where N is provided by the user.
     *
     * @param region The region for which the top populated countries will be retrieved.
     * @param N      The number of top populated countries to retrieve.
     * @return A list of all top N populated countries in a region, or an empty list if there is an error.
     */
    public ArrayList<CountryReport> getTopCountriesByRegion(String region, int N) {
        try {
            // SQL query to get countries in a region, ordered by population
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name AS Capital "
                    + "FROM country ctry, city cty "
                    + "WHERE ctry.Code = cty.CountryCode "
                    + "AND ctry.Capital = cty.ID "
                    + "AND ctry.Region = ? "
                    + "ORDER BY ctry.Population DESC "
                    + "LIMIT ?";

            // Prepare the SQL statement with the region parameter
            PreparedStatement prepStmt = getConnection().prepareStatement(query);
            prepStmt.setString(1, region);
            prepStmt.setInt(2, N);

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
}



