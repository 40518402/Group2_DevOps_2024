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
 * Represents a language report in the system.
 * This class stores report information such as language, total speakers and world percentage.
 */
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
     *
     * @return LanguageReport object instance as a string.
     */
    @Override
    public String toString() {
        return String.format("LanguageReport{Language = %s, Total_Speakers = %s, World_Percentage = %.2f%%}",
                getLanguage(),
                NumberFormat.getInstance().format(getTotalSpeakers()),
                getWorldPercentage());
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
     * Outputs to Markdown
     *
     * @param languages The list of languages to output.
     * @param filename The name of the outputted file.
     */
    public void outputLanguageReports(ArrayList<LanguageReport> languages, String filename) {
        // Check if languages is null
        if (languages == null || languages.isEmpty()) {
            System.out.println("No languages to output!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Language | Total Speakers | World Percentage |\r\n");
        sb.append("| --- | --- | --- |\r\n");
        // Loop over all languages in the list
        for (LanguageReport language : languages) {
            if (language == null) continue;
            sb.append("| " + language.getLanguage() + " | "
                    + NumberFormat.getInstance().format(language.getTotalSpeakers()) + " | "
                    + language.getWorldPercentage() + "%" + " |\r\n");
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
