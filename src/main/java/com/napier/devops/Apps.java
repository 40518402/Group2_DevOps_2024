package com.napier.devops;

import java.util.ArrayList;

/**
 * This class serves as the entry point for the application and generates the various reports
 * for the user.
 */
public class Apps
{
    public static void main(String[] args)
    {
        // Create new CityReport Object Instance
        LanguageReport languageReport = new LanguageReport();

        // Connect to database
        if(args.length < 1){
            languageReport.connect("localhost:33060", 30000);
        }else{
            languageReport.connect(args[0], Integer.parseInt(args[1]));
        }

        // The number of people who speak the following languages from the greatest number to smallest, including the percentage of the world population.
        ArrayList<LanguageReport> languageData = languageReport.getLanguageSpeakersData();
        languageReport.displayLanguages(languageData);

        // Disconnect from database
        languageReport.disconnect();
    }
}