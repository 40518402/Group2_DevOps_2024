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
        // Print header
        System.out.println(String.format("%-5s %-22s %-10s %-28s %20s %10s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (CountryReport country : countries) {
            String country_string =
                    String.format("%-5s %-22s %-10s %-28s %20s %10s",
                            country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(country_string);
        }
    }

    /**
     * Converts a result set row into a CountryReport object.
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
        country.setPopulation(rset.getInt("ctry.Population"));
        country.setCapital(rset.getString("Capital"));

        return country;
    }

    /**
     * Gets the top N populated countries in a continent where N is provided by the user.
     * @param continent The continent for which the top populated countries will be retrieved.
     * @param N         The number of top populated countries to retrieve.
     * @return A list of all top N populated countries in a continent, or null if there is an error.
     */
    public ArrayList<CountryReport> getTopCountriesByContinent(String continent, int N) {
        try {
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name As Capital "
                    + "FROM country ctry,  city cty "
                    + "WHERE ctry.Code = cty.CountryCode "
                    + "AND ctry.Capital = cty.ID AND Continent = ? "
                    + "ORDER BY Population DESC "
                    + "LIMIT ?";

            PreparedStatement prepStmt = getConnection().prepareStatement(query);
            prepStmt.setString(1, continent);
            prepStmt.setInt(2, N);

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

    /**
     * Gets all the countries in a region, organized by population (largest to smallest).
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
}
