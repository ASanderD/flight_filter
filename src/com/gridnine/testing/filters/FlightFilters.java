package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;

public interface FlightFilters {
    List<Flight> flightsFilter(List<Flight> flights);
}
