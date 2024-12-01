package es.ull.passengers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.es.ull.passengers.Passenger;
import main.java.es.ull.flights.Flight;

class FlightsTest {

      // Tests para inicializar un pasaajero
    @Test
    void testValidPassenger() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("12345678A", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("ES", passenger.getCountryCode());
    }

    // Test para comprobar el getIdentifier
    @Test
    void testGetIdentifier() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("12345678A", passenger.getIdentifier());
    }

    // Test para comprobar el getName
    @Test
    void testGetName() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("John Doe", passenger.getName());
    }

    // Test para comprobar el getCountryCode
    @Test
    void testGetCountryCode() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("ES", passenger.getCountryCode());
    }

    // Test para comprobar el getFlight
    @Test
    void testGetFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    // Test para comprobar el joinFlight
    @Test
    void testJoinFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    // Test para comprobar el joinFlight con un vuelo lleno
    @Test
    void testJoinFullFlight() {
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        Flight flight = new Flight("AA1234", 1);
        passenger1.joinFlight(flight);
        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight));
    }

    // Test para comprobar el setFlight
    @Test
    void testSetFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.setFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    // Test para comprobar el toString
    @Test
    void testToString() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("Passenger John Doe with identifier: 12345678A from ES", passenger.toString());
    }

    // Test para comprobar los fallos----------------

    // Test para comprobar un fallo en el constructor
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("12345678A", "John Doe", "XX"));
    }

    // Test para comprobar un fallo en el getIdentifier
    @Test
    void testGetIdentifierFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("12345678B", passenger.getIdentifier());
    }

    // Test para comprobar un fallo en el getName
    @Test
    void testGetNameFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("Jane Doe", passenger.getName());
    }

    // Test para comprobar un fallo en el getCountryCode
    @Test
    void testGetCountryCodeFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("FR", passenger.getCountryCode());
    }

    // Test para comprobar un fallo en el getFlight
    @Test
    void testGetFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertNotEquals(null, passenger.getFlight());
    }

    // Test para comprobar un fallo en el joinFlight
    @Test
    void testJoinFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertNotEquals(null, passenger.getFlight());
        assertNotEquals(0, flight.getNumberOfPassengers());
    }

    // Test para comprobar un fallo en el setFlight
    @Test
    void testSetFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.setFlight(flight);
        assertNotEquals(null, passenger.getFlight());
    }

    // Test para comprobar un fallo en el toString
    @Test
    void testToStringFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("Passenger Jane Doe with identifier: 12345678A from ES", passenger.toString());
    }
}