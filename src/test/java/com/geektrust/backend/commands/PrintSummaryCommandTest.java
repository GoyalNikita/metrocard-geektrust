package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.geektrust.backend.dtos.CollectionSummary;
import com.geektrust.backend.dtos.PassengerSummary;
import com.geektrust.backend.dtos.PassengerTypeCount;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.services.StationService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrintSummaryCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private StationService stationServiceMock;

    @InjectMocks
    private PrintSummaryCommand printSummaryCommand;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of PrintSummaryCommand should print the collectionSummary and passengerSummary of each station")
    public void execute_shouldPrintCollectionAndPassengerSummary() {

        List<String> expectedOutput = Arrays.asList(
                "TOTAL_COLLECTION CENTRAL 457 50",
                "PASSENGER_TYPE_SUMMARY",
                "ADULT 2",
                "SENIOR_CITIZEN 1",
                "TOTAL_COLLECTION AIRPORT 252 100",
                "PASSENGER_TYPE_SUMMARY",
                "ADULT 1",
                "KID 1",
                "SENIOR_CITIZEN 1");

        Station station1 = new Station("1", "CENTRAL");
        Station station2 = new Station("2", "AIRPORT");
        List<Station> stations = Arrays.asList(station1, station2);

        CollectionSummary collectionSummaryCentral = new CollectionSummary("CENTRAL", 457, 50);
        CollectionSummary collectionSummaryAirport = new CollectionSummary("AIRPORT", 252, 100);

        PassengerTypeCount passengerTypeCount1 = new PassengerTypeCount(PassengerType.ADULT, 2);
        PassengerTypeCount passengerTypeCount2 = new PassengerTypeCount(PassengerType.SENIOR_CITIZEN, 1);
        PassengerTypeCount passengerTypeCount3 = new PassengerTypeCount(PassengerType.ADULT, 1);
        PassengerTypeCount passengerTypeCount4 = new PassengerTypeCount(PassengerType.KID, 1);
        PassengerTypeCount passengerTypeCount5 = new PassengerTypeCount(PassengerType.SENIOR_CITIZEN, 1);

        List<PassengerTypeCount> passengerTypeCountsCentral = Arrays.asList(passengerTypeCount1, passengerTypeCount2);
        List<PassengerTypeCount> passengerTypeCountsAirport = Arrays.asList(passengerTypeCount3, passengerTypeCount4,
                passengerTypeCount5);

        PassengerSummary passengerSummaryCentral = new PassengerSummary(passengerTypeCountsCentral);
        PassengerSummary passengerSummaryAirport = new PassengerSummary(passengerTypeCountsAirport);

        List<String> tokens = Arrays.asList("PRINT_SUMMARY");

        when(stationServiceMock.getAllStations()).thenReturn(stations);
        when(stationServiceMock.getCollectionSummary(station1)).thenReturn(collectionSummaryCentral);
        when(stationServiceMock.getPassengerSummary(station1)).thenReturn(passengerSummaryCentral);
        when(stationServiceMock.getCollectionSummary(station2)).thenReturn(collectionSummaryAirport);
        when(stationServiceMock.getPassengerSummary(station2)).thenReturn(passengerSummaryAirport);

        printSummaryCommand.execute(tokens);
        List<String> actualOutput = getActualOutput();

        assertOutput(expectedOutput, actualOutput);
        verify(stationServiceMock, times(1)).getAllStations();
        verify(stationServiceMock, times(2)).getCollectionSummary(any(Station.class));
        verify(stationServiceMock, times(2)).getPassengerSummary(any(Station.class));
    }

    private List<String> getActualOutput() {
        return Arrays.stream(outputStreamCaptor.toString().trim().split("\\r?\\n"))
                .collect(Collectors.toList());
    }

    private void assertOutput(List<String> expected, List<String> actual) {
        Assertions.assertEquals(expected.size(), actual.size(), "The number of lines in the output does not match.");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), actual.get(i), "Msomething went wrong at line " + (i + 1));
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
