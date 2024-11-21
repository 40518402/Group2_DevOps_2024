package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeographicalLevelUnitTest {

    @Test
    void testEnumValues() {
        assertEquals(GeographicLevel.COUNTRY, GeographicLevel.valueOf("COUNTRY"));
        assertEquals(GeographicLevel.CONTINENT, GeographicLevel.valueOf("CONTINENT"));
        assertEquals(GeographicLevel.REGION, GeographicLevel.valueOf("REGION"));
        assertEquals(GeographicLevel.CITY, GeographicLevel.valueOf("CITY"));
        assertEquals(GeographicLevel.DISTRICT, GeographicLevel.valueOf("DISTRICT"));
    }
}
