package de.latlon.xplanbox.api.commons;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ValidatorConverterTest {

    @Test
    public void detectOrCreateValidationName() {
        assertThat( ValidatorConverter.detectOrCreateValidationName(null), containsString("-"));
    }

    @Test
    public void testDetectOrCreateValidationName() {
    }

    @Test
    public void createValidationSettings() {
    }
}