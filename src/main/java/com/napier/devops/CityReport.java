package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityReport extends Report {
    short id;
    String country;
    String district;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void displayCities(ArrayList<CityReport> cities) {
        // Check countries is not null
        if (cities == null)
        {
            System.out.println("No cities found!");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s %-40s %-25s %-15s", "Name", "Country", "District", "Population"));
        // Loop over all countries in the list
        for (CityReport city : cities) {
            if (city == null) {
                continue;
            }
            String city_string =
                    String.format("%-20s %-40s %-25s %-15s",
                            city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation());
            System.out.println(city_string);
        }
    }

    /**
     * Converts a result set row into a CityReport object.
     *
     * @param rset Holds query result set containing city data.
     * @return A CityReport instance.
     * @throws SQLException If SQL error occurs while accessing data.
     */
    public CityReport mapToCity(ResultSet rset) throws SQLException {
        CityReport city = new CityReport();
        city.setId(rset.getShort("cty.ID"));
        city.setName(rset.getString("cty.Name"));
        city.setCountry(rset.getString("Country"));
        city.setDistrict(rset.getString("cty.District"));
        city.setPopulation(rset.getLong("cty.Population"));

        return city;
    }

    /**
     * Retrieves all the cities in a region in descending order, or the top N populated cities if N is not null.
     *
     * @param region The region for which the top populated cities will be retrieved.
     * @param N The number of top populated cities to retrieve.
     * @return A list of cities in a region, or null if there is an error.
     */
    public ArrayList<CityReport> getCitiesInRegion(String region, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.CountryCode = ctry.Code "
                + "WHERE ctry.Region = ? "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement
        try(PreparedStatement prepStmt = getConnection().prepareStatement(query)) {
            prepStmt.setString(1, region);

            if (N != null) {
                prepStmt.setInt(2, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CityReport> cities = new ArrayList<>();

            // Loop through the result set
            while (rset.next()) {
                cities.add(mapToCity(rset));
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CityReport> getCitiesInRegion(String region) {
        return getCitiesInRegion(region, null);
    }

}
