package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Represents a country
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
        // Loop over all employees in the list
        for (CountryReport country : countries) {
            String country_string =
                    String.format("%-5s %-22s %-10s %-28s %20s %10s",
                            country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(country_string);
        }
    }

    /**
     * Gets all the countries in a continent.
     *
     * @return A list of all countries in a continent, or null if there is an error.
     */
    public ArrayList<CountryReport> getTopCountriesByContinent(String continent, int N) {
        try {
            String query = "SELECT ctry.Code, ctry.Name, ctry.Continent, ctry.Region, ctry.Population, cty.Name "
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
                CountryReport country = new CountryReport();
                country.setCode(rset.getString("ctry.Code"));
                country.setName(rset.getString("ctry.Name"));
                country.setContinent(rset.getString("ctry.Continent"));
                country.setRegion(rset.getString("ctry.Region"));
                country.setPopulation(rset.getInt("ctry.Population"));
                country.setCapital(rset.getString("cty.Name"));

                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details.");
            return null;
        }
    }
}
