package com.gridnine.testing;

import com.gridnine.testing.filters.impl.Flights_SegmentsWithArrivalDateBeforeDepartureDate;
import com.gridnine.testing.filters.FlightFilters;
import com.gridnine.testing.filters.impl.Flights_DepartureDateBeforeActualTime;
import com.gridnine.testing.filters.impl.Flight_IntervalWithoutFlightsMoreThanTwoHours;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilters flightDepartureDateBeforeActualTime = new Flights_DepartureDateBeforeActualTime();
        FlightFilters flightWithArrivalDateBeforeDepartureDate = new Flights_SegmentsWithArrivalDateBeforeDepartureDate();
        FlightFilters flight_IntervalWithoutFlightsMoreThanTwoHours = new Flight_IntervalWithoutFlightsMoreThanTwoHours();

        System.out.println("Тестовый набор перелетов:");
        flights.forEach(flight -> System.out.println(flight.toString()));
        System.out.println();
        System.out.println("Исключены перелеты с вылетом до текущего момента времени:");
        flightDepartureDateBeforeActualTime.flightsFilter(flights).forEach(System.out::println);
        System.out.println();
        System.out.println("Исключены перелеты с сегментами с датой прилёта раньше даты вылета:");
        flightWithArrivalDateBeforeDepartureDate.flightsFilter(flights).forEach(System.out::println);
        System.out.println();
        System.out.println("Исключены перелеты, где общее время, проведённое на земле, превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)");
        flight_IntervalWithoutFlightsMoreThanTwoHours.flightsFilter(flights).forEach(System.out::println);
        System.out.println();
    }
}