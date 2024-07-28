//Вылет до текущего момента времени.

package com.gridnine.testing.filters.impl;

import com.gridnine.testing.check.CheckFlights;
import com.gridnine.testing.filters.FlightFilters;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class Flights_DepartureDateBeforeActualTime implements FlightFilters {
    /**
     * Filters of flights where departure before the current time
     *
     * @param flights - list of flights for filter
     * @return A list of flights where departure before the current time
     */
    @Override
    public List<Flight> flightsFilter(List<Flight> flights) {
        CheckFlights.checkFlights(flights);
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))).toList();
    }
}