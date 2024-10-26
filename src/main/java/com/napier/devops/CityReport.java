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

    public void displayCity(ArrayList<CityReport> cities) {
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

    public CityReport mapToCity(ResultSet rset) throws SQLException {
        CityReport city = new CityReport();
        city.setId(rset.getShort(1));
        city.setName(rset.getString(2));
        city.setCountry(rset.getString(3));
        city.setDistrict(rset.getString(4));
        city.setPopulation(rset.getLong(5));

        return city;
    }

}
