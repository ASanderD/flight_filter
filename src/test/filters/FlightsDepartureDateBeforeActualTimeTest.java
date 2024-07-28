package test.filters;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.exception.FlightsNotFoundException;
import com.gridnine.testing.filters.impl.Flights_DepartureDateBeforeActualTime;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightsDepartureDateBeforeActualTimeTest {
    private final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

    @Test
    void flightsFilter() {
        Flights_DepartureDateBeforeActualTime flights_departureDateBeforeActualTime = new Flights_DepartureDateBeforeActualTime();
        List<Flight> actual = flights_departureDateBeforeActualTime.flightsFilter(flights);
        List<Flight> expected = flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))).toList();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        flights.clear();
        assertThrows(FlightsNotFoundException.class, () -> flights_departureDateBeforeActualTime.flightsFilter(flights));
        assertEquals(flights.size(), 0);
    }

    @Test
    public void flightsFilterNegativeTest() {
        Flights_DepartureDateBeforeActualTime flights_departureDateBeforeActualTime = new Flights_DepartureDateBeforeActualTime();
        flights.clear();
        assertThrows(FlightsNotFoundException.class, () -> flights_departureDateBeforeActualTime.flightsFilter(flights));
        Assertions.assertEquals(flights.size(), 0);
    }

    private void checkFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new FlightsNotFoundException("No flights found");
        }
    }
}