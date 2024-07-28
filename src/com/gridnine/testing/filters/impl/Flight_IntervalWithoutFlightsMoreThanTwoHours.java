/*
Перелеты, где общее время, проведённое на земле, превышает два часа
(время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).
*/

package com.gridnine.testing.filters.impl;

import com.gridnine.testing.check.CheckFlights;
import com.gridnine.testing.filters.FlightFilters;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;

public class Flight_IntervalWithoutFlightsMoreThanTwoHours implements FlightFilters {
    private static final long LIMIT_TIME_ON_LAND = 7200L;

    /**
     * Filters of flights where the total time spent on the ground exceeds two hours
     *
     * @param flights - list of flights for filter
     * @return A list of flights where the total time spent on the ground exceeds two hours
     */
    @Override
    public List<Flight> flightsFilter(List<Flight> flights) {
        CheckFlights.checkFlights(flights);
        return flights.stream()
                .filter(flight -> flight.getSegments().size() > 1)
                .filter(flight -> checkTimeOnLand(flight.getSegments()))
                .toList();
    }

    private boolean checkTimeOnLand(List<Segment> segments) {
        long timeOnLand = 0;
        for (int i = 1; i < segments.size(); i++) {
            timeOnLand += Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate()).getSeconds();
        }
        return timeOnLand <= LIMIT_TIME_ON_LAND;
    }
}