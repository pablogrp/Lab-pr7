package es.ull.flights;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.es.ull.passengers.Passenger;
import main.java.es.ull.flights.Flight;

class FlightsTest {

    @Test
    void testValidFlightNumber() {
        Flight flight = new Flight("AA1234", 100);
        assertEquals("AA1234", flight.getFlightNumber());
    }

    // Test añadir pasajero
    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    // Test añadir pasajero a un vuelo lleno
    @Test
    void testAddPassengerToFullFlight() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        assertTrue(flight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

    // Test eliminar pasajero
    @Test
    void testRemovePassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    // Test eliminar pasajero que no está en el vuelo
    @Test
    void testRemoveNonExistentPassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertFalse(flight.removePassenger(passenger));
    }

    // Test eliminar pasajero de un vuelo lleno
    @Test
    void testRemovePassengerFromFullFlight() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    // Test para comprobar que el número de pasajeros es correcto
    @Test
    void testNumberOfPassengers() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        assertEquals(2, flight.getNumberOfPassengers());
    }

    // Test para inicializar un vuelo
    @Test
    void testInitializeFlight() {
        Flight flight = new Flight("AA1234", 100);
        assertEquals("AA1234", flight.getFlightNumber());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    // Test para comprobar que el número de pasajeros es correcto
    @Test
    void testNumberOfPassengersAfterRemoving() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        flight.removePassenger(passenger1);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    // test para comprobar que falla al añadir un pasajero con un código de país inválido
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("12345678A", "John Doe", "XX"));
    }

    // test para comprobar que falla al añadir un pasajero y no hay suficientes asientos
    @Test
    void testNotEnoughSeats() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        flight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

}
