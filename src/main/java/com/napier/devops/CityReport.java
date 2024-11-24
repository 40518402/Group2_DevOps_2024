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
 * Represents a city report in the system.
 * This class stores report information such as id, name, country, district and population.
 */
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

    /**
     *
     * @return CityReport object instance as a string.
     */
    @Override
    public String toString() {
        return String.format("CityReport{Id = %d, Name = %s, Country = %s, District = %s, Population = %s}",
                getId(),
                getName() != null ? getName() : "N/A",
                getCountry() != null ? getCountry() : "N/A",
                getDistrict() != null ? getDistrict() : "N/A",
                NumberFormat.getInstance().format(getPopulation()));
    }

    /**
     * Prints a list of cities.
     *
     * @param cities The list of countries to print.
     * @param isCapital Changes console output if list consists of only capital cities.
     */
    public void displayCities(ArrayList<CityReport> cities, boolean isCapital) {
        // Check countries is not null
        if (cities == null || cities.isEmpty())
        {
            System.out.println("No cities found!");
            return;
        }

        // Print header
        if (isCapital) {
            System.out.printf("%-20s %-40s %-15s%n", "Name", "Country", "Population");
        } else {
            System.out.printf("%-20s %-40s %-25s %-15s%n", "Name", "Country", "District", "Population");
        }

        // Loop over all countries in the list
        for (CityReport city : cities) {
            if (city == null) {
                continue;
            }
            String city_string = isCapital ? String.format("%-20s %-40s %-15s", city.getName(), city.getCountry(), NumberFormat.getInstance().format(city.getPopulation()))
                    : String.format("%-20s %-40s %-25s %-15s", city.getName(), city.getCountry(), city.getDistrict(), NumberFormat.getInstance().format(city.getPopulation()));
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
     * Outputs to Markdown
     *
     * @param cities The list of cities to output.
     * @param filename The name of the outputted file.
     * @param isCapital Changes table output if list consists of only capital cities.
     */
    public void outputCityReports(ArrayList<CityReport> cities, String filename, boolean isCapital) {
        // Check cities is null
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to output!");
            return;
        }

        if (filename == null) {
            System.out.println("No filename was entered!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        if (isCapital) {
            sb.append("| Capital City | Country | Population |\r\n");
            sb.append("| --- | --- | --- |\r\n");
        } else {
            sb.append("| City | Country | District | Population |\r\n");
            sb.append("| --- | --- | --- | --- |\r\n");
        }

        // Loop over all cities in the list
        for (CityReport city : cities) {
            if (city == null) continue;

            if (isCapital) {
                sb.append("| ").append(city.getName()).append(" | ").append(city.getCountry()).append(" | ").append(NumberFormat.getInstance().format(city.getPopulation())).append(" |\r\n");
            } else {
                sb.append("| ").append(city.getName()).append(" | ").append(city.getCountry()).append(" | ").append(city.getDistrict()).append(" | ").append(NumberFormat.getInstance().format(city.getPopulation())).append(" |\r\n");
            }
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

    // --------------------------------------- City Query Methods --------------------------------------------------------

    /**
     * Retrieves all cities in the world organized by population from largest to smallest.
     *
     * @param N The number of top populated cities to retrieve.
     * @return A list of cities in the world, ordered by population in descending order.
     */
    public ArrayList<CityReport> getCitiesInWorld(Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.CountryCode = ctry.Code "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {

            if (N != null) {
                prepStmt.setInt(1, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CityReport> cities = new ArrayList<>();

            while (rset.next()) {
                cities.add(mapToCity(rset));
            }

            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CityReport> getCitiesInWorld() {
        return getCitiesInWorld(null);
    }

    /**
     * Retrieves all cities in a specified country organized by population from largest to smallest.
     *
     * @param country The name of the country.
     * @param N The number of top populated cities to retrieve.
     * @return A list of cities in the country, ordered by population in descending order.
     */
    public ArrayList<CityReport> getCitiesInCountry(String country, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.CountryCode = ctry.Code "
                + "WHERE ctry.Name = ? "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, country);

            if (N != null) {
                prepStmt.setInt(2, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CityReport> cities = new ArrayList<>();

            while (rset.next()) {
                cities.add(mapToCity(rset));
            }

            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details.");
            return null;
        }
    }

    // Using method overloading to set a default value of null for N.
    public ArrayList<CityReport> getCitiesInCountry(String country) {
        return getCitiesInCountry(country, null);
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
        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
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

    /**
     * Retrieves all the cities in a district in descending order, or the top N populated cities if N is not null.
     *
     * @param district The district for which the top populated cities will be retrieved.
     * @param N The number of top populated cities to retrieve.
     * @return A list of cities in a district, or null if there is an error.
     */
    public ArrayList<CityReport> getCitiesInDistrict(String district, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.CountryCode = ctry.Code "
                + "WHERE cty.District = ? "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement
        try (PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, district);

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
    // Method overloading to give N default amount if not specified by the end user.
    public ArrayList<CityReport> getCitiesInDistrict(String district) {
        return getCitiesInDistrict(district, null);
    }

    /**
     * Retrieves all cities in a specified country organized by population from largest to smallest.
     *
     * @param continent The name of the country.
     * @return A list of cities in the continent, ordered by population in descending order.
     */
    public ArrayList<CityReport> getCitiesInContinent(String continent, Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.CountryCode = ctry.Code "
                + "WHERE ctry.Continent = ? "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            prepStmt.setString(1, continent);

            if (N != null) {
                prepStmt.setInt(2, N);
            }

            ResultSet rset = prepStmt.executeQuery();

            ArrayList<CityReport> cities = new ArrayList<>();

            while (rset.next()) {
                cities.add(mapToCity(rset));
            }

            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details.");
            return null;
        }
    }

    // Method overloading to give N default amount if not specified by the end user.
    public ArrayList<CityReport> getCitiesInContinent(String continent) {
        return getCitiesInContinent(continent, null);
    }

    // --------------------------------------- Capital City Query Methods --------------------------------------------------------

    /**
     * Retrieves all capital cities in the world in descending order, or top N populated capital cities when prompted by user.
     *
     * @param N The number of top populated capital cities to retrieve.
     * @return A list of capital cities in a region, or null if there is an error.
     */
    public ArrayList<CityReport> getCapitalCitiesWorldWide(Integer N) {
        String query = "SELECT cty.ID, cty.Name, ctry.Name AS Country, cty.District, cty.Population "
                + "FROM city cty "
                + "JOIN country ctry ON cty.ID = ctry.Capital "
                + "ORDER BY cty.Population DESC";

        if (N != null) {
            query += " LIMIT ?";
        }

        // Prepare the SQL statement
        try (PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
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
        return getCapitalCitiesWorldWide(null);
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
        try(PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
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

        try (PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
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
