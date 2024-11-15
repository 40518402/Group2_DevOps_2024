package com.napier.devops;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class LanguageReport extends Report {
    private String language;
    private long totalSpeakers;
    private float worldPercentage;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getTotalSpeakers() {
        return totalSpeakers;
    }

    public void setTotalSpeakers(long totalSpeakers) {
        this.totalSpeakers = totalSpeakers;
    }

    public float getWorldPercentage() {
        return worldPercentage;
    }

    public void setWorldPercentage(float worldPercentage) {
        this.worldPercentage = worldPercentage;
    }

    /**
     * Prints a list of language data.
     *
     * @param languages The list of language data to print.
     */
    public void displayLanguages(ArrayList<LanguageReport> languages) {
        // Check if languages list is empty
        if (languages == null || languages.isEmpty()) {
            System.out.println("No language data found!");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s %-25s %-15s", "Language", "Total Speakers", "World Percentage"));
        for (LanguageReport language : languages) {
            if (language == null) {
                continue;
            }
            String languageString = String.format("%-20s %-25s %-15s",
                    language.getLanguage(),
                    NumberFormat.getInstance().format(language.getTotalSpeakers()),
                    language.getWorldPercentage() + "%");
            System.out.println(languageString);
        }
    }

    /**
     * Maps a result set row to a LanguageReport object.
     *
     * @param rset The result set containing language data.
     * @return A LanguageReport object.
     * @throws SQLException If an SQL error occurs.
     */
    public LanguageReport mapToLanguageReport(ResultSet rset) throws SQLException {
        LanguageReport languageReport = new LanguageReport();
        languageReport.setLanguage(rset.getString("Language"));
        languageReport.setTotalSpeakers(rset.getLong("Total_Speakers"));
        languageReport.setWorldPercentage(rset.getFloat("World_Percentage"));
        return languageReport;
    }

    /**
     * Retrieves data about languages sorted by the total number of speakers.
     *
     * @return A list of language data.
     */
    public ArrayList<LanguageReport> getLanguageSpeakersData() {
        String query = """
            SELECT lang.Language,
                   SUM(ROUND((ctry.Population * lang.Percentage) / 100)) AS Total_Speakers,
                   ROUND(SUM(ROUND((ctry.Population * lang.Percentage) / 100)) * 100 / 
                         (SELECT SUM(ctry.Population) FROM country ctry), 2) AS World_Percentage
            FROM countrylanguage lang
            JOIN country ctry ON lang.CountryCode = ctry.Code
            WHERE lang.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
            GROUP BY lang.Language
            ORDER BY Total_Speakers DESC;
            """;

        try (PreparedStatement prepStmt = getDatabaseConnection().prepareStatement(query)) {
            ResultSet rset = prepStmt.executeQuery();

            ArrayList<LanguageReport> languageReports = new ArrayList<>();

            while (rset.next()) {
                languageReports.add(mapToLanguageReport(rset));
            }

            return languageReports;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve language data.");
            return null;
        }
    }
}
