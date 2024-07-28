package test.filters;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.exception.FlightsNotFoundException;
import com.gridnine.testing.filters.impl.Flights_SegmentsWithArrivalDateBeforeDepartureDate;
import com.gridnine.testing.model.Flight;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FlightsSegmentsWithArrivalDateBeforeDepartureDateTest {
    private final List<Flight> flights=new ArrayList<>(FlightBuilder.createFlights());

    @Test
    public void flightsFilter() {
        Flights_SegmentsWithArrivalDateBeforeDepartureDate flights_segmentsWithArrivalDateBeforeDepartureDate = new Flights_SegmentsWithArrivalDateBeforeDepartureDate();
        List<Flight> actual = flights_segmentsWithArrivalDateBeforeDepartureDate.flightsFilter(flights);
        List<Flight> expected = flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
        flights.clear();
        assertThrows(FlightsNotFoundException.class, () -> flights_segmentsWithArrivalDateBeforeDepartureDate.flightsFilter(flights));
        Assertions.assertEquals(flights.size(), 0);
    }
    @Test
    public void flightsFilterNegativeTest() {
        Flights_SegmentsWithArrivalDateBeforeDepartureDate flights_segmentsWithArrivalDateBeforeDepartureDate = new Flights_SegmentsWithArrivalDateBeforeDepartureDate();
        flights.clear();
        assertThrows(FlightsNotFoundException.class, () -> flights_segmentsWithArrivalDateBeforeDepartureDate.flightsFilter(flights));
        Assertions.assertEquals(flights.size(), 0);
    }
    private void checkFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new FlightsNotFoundException("No flights found");
        }
    }
}

