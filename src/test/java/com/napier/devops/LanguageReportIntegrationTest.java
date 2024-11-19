package com.napier.devops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageReportIntegrationTest {
    static LanguageReport languageReport;

    @BeforeAll
    static void init() {
        languageReport = new LanguageReport();
        languageReport.connect("localhost:33060", 30000);

        assertNotNull(languageReport.getDatabaseConnection());
    }

    @AfterAll
    static void close() {
        languageReport.disconnect();
    }

    @Test
    void getLanguageSpeakersDataNormalTest() {
        ArrayList<LanguageReport> languages = languageReport.getLanguageSpeakersData();

        assertNotNull(languages, "Language list should not be null.");
        assertFalse(languages.isEmpty(), "Language list should not be empty.");
        assertEquals(5, languages.size(), "Language list should contain 5 languages.");

        LanguageReport topLanguage = languages.get(0);
        assertEquals("Chinese", topLanguage.getLanguage());
        assertEquals(1191843539, topLanguage.getTotalSpeakers());
        assertEquals(19.61f, topLanguage.getWorldPercentage());
    }
}
