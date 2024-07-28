package test.filters;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.impl.Flight_IntervalWithoutFlightsMoreThanTwoHours;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightIntervalWithoutFlightsMoreThanTwoHoursTest {
    private final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
    private static final long LIMIT_TIME_ON_LAND = 7200L;

    @Test
    public void flightsFilterPositiveTest() {
        Flight_IntervalWithoutFlightsMoreThanTwoHours flight_IntervalWithoutFlightsMoreThanTwoHours = new Flight_IntervalWithoutFlightsMoreThanTwoHours();
        List<Flight> actual = flight_IntervalWithoutFlightsMoreThanTwoHours.flightsFilter(flights);
        List<Flight> expected = flights.stream()
                .filter(flight -> flight.getSegments().size() > 1)
                .filter(flight -> checkTimeOnLand(flight.getSegments()))
                .toList();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }

    private boolean checkTimeOnLand(List<Segment> segments) {
        long timeOnLand = 0;
        for (int i = 1; i < segments.size(); i++) {
            timeOnLand += Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate()).getSeconds();
        }
        return timeOnLand <= LIMIT_TIME_ON_LAND;
    }
}
