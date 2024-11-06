package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    public void displayCapitalCities(ArrayList<CityReport> capitalCities) {
        // Check capital cities is not null
        if (capitalCities == null)
        {
            System.out.println("No capital cities found!");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s %-40s %-15s", "Name", "Country", "Population"));
        // Loop over all cities in the list
        for (CityReport capitalCity : capitalCities) {
            if (capitalCity == null) {
                continue;
            }
            String city_string =
                    String.format("%-20s %-40s %-15s",
                            capitalCity.getName(), capitalCity.getCountry(), capitalCity.getPopulation());
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
     * Retrieves all capital cities in the world in descending order, or top N populated capital cities when prompted by user.
     *
     * @param N The number of top populated capital cities to retrieve.
     * @return A list of capital cities in a region, or null if there is an error.
     */
    public ArrayList<CityReport> getCapitalCitiesWorldwide(Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.ID = ctry.Capital "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement
        try (PreparedStatement prepStmt = getConnection().prepareStatement(query)) {
            if (N != null) {
                prepStmt.setInt(1, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CityReport> capitalCities = new ArrayList<>();

            // Loop through the result set
            while (rset.next()) {
                capitalCities.add(mapToCity(rset));
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }

    // Overloaded method with a default value of null for N once not specified.
    public ArrayList<CityReport> getCapitalCitiesWorldWide() {
        return getCapitalCitiesWorldwide(null);
    }

    /**
     * Retrieves all the capital cities in a region in descending order, or the top N populated capital cities if N is not null.
     *
     * @param region The region for which the top populated capital cities will be retrieved.
     * @param N The number of top populated capital cities to retrieve.
     * @return A list of capital cities in a region, or null if there is an error.
     */
    public ArrayList<CityReport> getCapitalCitiesInRegion(String region, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.ID = ctry.Capital "
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

            ArrayList<CityReport> capitalCities = new ArrayList<>();

            // Loop through the result set
            while (rset.next()) {
                capitalCities.add(mapToCity(rset));
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CityReport> getCapitalCitiesInRegion(String region) {
        return getCapitalCitiesInRegion(region, null);
    }

    /**
     * Retrieves all the capital cities in a continent in descending order, or the top N populated capital cities if N is not null.
     *
     * @param continent The region for which the top populated capital cities will be retrieved.
     * @param N The number of top populated capital cities to retrieve.
     * @return A list of capital cities in a continent, or null if there is an error.
     */
    public ArrayList<CityReport> getCapitalCitiesInContinent(String continent, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.ID = ctry.Capital "
                + "WHERE ctry.Continent = ? "
                + "ORDER BY cty.Population DESC";

        if (N != null) { query += " LIMIT ?"; }

        try (PreparedStatement prepStmt = getConnection().prepareStatement(query)) {
            prepStmt.setString(1, continent);

            if (N != null) {prepStmt.setInt(2, N);}

            ResultSet rset = prepStmt.executeQuery();
            ArrayList<CityReport> capitalCities = new ArrayList<>();

            // Loop through the result set
            while (rset.next()) {
                capitalCities.add(mapToCity(rset));
            }
            return capitalCities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital cities in the continent.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CityReport> getCapitalCitiesInContinent(String continent) {
        return getCapitalCitiesInContinent(continent, null);
    }


}
