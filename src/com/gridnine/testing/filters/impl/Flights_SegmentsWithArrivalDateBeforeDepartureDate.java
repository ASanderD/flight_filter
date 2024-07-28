//Сегменты с датой прилёта раньше даты вылета.

package com.gridnine.testing.filters.impl;

import com.gridnine.testing.check.CheckFlights;
import com.gridnine.testing.filters.FlightFilters;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Flights_SegmentsWithArrivalDateBeforeDepartureDate implements FlightFilters {
    /**
     * Filters of flights where segments with arrival date before departure date
     *
     * @param flights - list of flights for filter
     * @return A list of flights where segments with arrival date before departure date
     */
    @Override
    public List<Flight> flightsFilter(List<Flight> flights) {
        CheckFlights.checkFlights(flights);
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();
    }
}