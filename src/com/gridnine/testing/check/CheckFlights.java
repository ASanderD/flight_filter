package com.gridnine.testing.check;

import com.gridnine.testing.exception.FlightsNotFoundException;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class CheckFlights {
    /**
     * Check flights for empty list
     *
     * @param flights - list of flights for filter
     */
    public static void checkFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new FlightsNotFoundException("No flights found");
        }
    }
}
