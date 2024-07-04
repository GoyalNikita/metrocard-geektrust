package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test()
    @DisplayName("Integration Test")
    public void Application_Test() {

        String[] arguments = new String[] { "sample_input/input1.txt" };
        List<String> expectedOutput = Arrays.asList(
                "TOTAL_COLLECTION CENTRAL 300 0",
                "PASSENGER_TYPE_SUMMARY",
                "ADULT 1",
                "SENIOR_CITIZEN 1",
                "TOTAL_COLLECTION AIRPORT 403 100",
                "PASSENGER_TYPE_SUMMARY",
                "ADULT 2",
                "KID 2");

        App.main(arguments);
        List<String> actualOutput = getActualOutput();

        assertOutput(expectedOutput, actualOutput);
    }

    private List<String> getActualOutput() {
        return Arrays.stream(outputStreamCaptor.toString().trim().split("\\r?\\n"))
                .collect(Collectors.toList());
    }

    private void assertOutput(List<String> expected, List<String> actual) {
        Assertions.assertEquals(expected.size(), actual.size(), "The number of lines in the output does not match.");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), actual.get(i), "something went wrong at line " + (i + 1));
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
