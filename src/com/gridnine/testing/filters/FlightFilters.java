package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilters {
    List<Flight> flightsFilter(List<Flight> flights);
}
