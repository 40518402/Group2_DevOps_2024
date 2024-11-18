package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LanguageReportUnitTest {
    static LanguageReport languageReport;

    @BeforeAll
    static void init() {

        languageReport = new LanguageReport();
    }

    @Test
    void languageGettersTest() {
        LanguageReport english = new LanguageReport();
        english.setLanguage("English");
        english.setTotalSpeakers(347077867);
        english.setWorldPercentage(5.71f);

        assertEquals("English", english.getLanguage());
        assertEquals(347077867, english.getTotalSpeakers());
        assertEquals(5.71f, english.getWorldPercentage());

    }

    @Test
    void toStringLanguageTestEmpty() {
        LanguageReport english = new LanguageReport();
        System.out.println(english.toString());
    }

    @Test
    void toStringLanguageTestContainsNullValue() {
        LanguageReport english = new LanguageReport();
        english.setLanguage(null);
        english.setTotalSpeakers(0);
        english.setWorldPercentage(0f);
        System.out.println(english.toString());
    }

    @Test
    void toStringLanguageTestNormal() {
        LanguageReport english = new LanguageReport();
        english.setLanguage("English");
        english.setTotalSpeakers(347077867);
        english.setWorldPercentage(5.71f);

        System.out.println(english.toString());
    }

    @Test
    void displayLanguagesTestNull() {
        languageReport.displayLanguages(null);
    }

    @Test
    void displayLanguagesTestEmpty() {
        ArrayList<LanguageReport> languages = new ArrayList<LanguageReport>();
        languageReport.displayLanguages(languages);
    }

    @Test
    void displayLanguagesTestContainsNull() {
        ArrayList<LanguageReport> languages = new ArrayList<LanguageReport>();
        languages.add(null);
        languageReport.displayLanguages(languages);
    }

    @Test
    void displayLanguagesTestNormal() {
        ArrayList<LanguageReport> languages = new ArrayList<LanguageReport>();
        LanguageReport english = new LanguageReport();
        english.setLanguage("English");
        english.setTotalSpeakers(347077867);
        english.setWorldPercentage(5.71f);
        languages.add(english);
        languageReport.displayLanguages(languages);
    }
}
