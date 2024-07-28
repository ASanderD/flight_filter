package test.check;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.exception.FlightsNotFoundException;
import com.gridnine.testing.filters.impl.Flights_DepartureDateBeforeActualTime;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class CheckFlightsTest {
    private final List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

    @Test
    void checkFlights() {
        Flights_DepartureDateBeforeActualTime flights_departureDateBeforeActualTime = new Flights_DepartureDateBeforeActualTime();
        flights.clear();
        assertThrows(FlightsNotFoundException.class, () -> flights_departureDateBeforeActualTime.flightsFilter(flights));
    }
}