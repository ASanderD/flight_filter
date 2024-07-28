package test.filters;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.impl.Flights_SegmentsWithArrivalDateBeforeDepartureDate;
import com.gridnine.testing.model.Flight;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightsSegmentsWithArrivalDateBeforeDepartureDateTest {
    private final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

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
    }
}

