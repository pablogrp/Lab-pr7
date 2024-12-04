/**
 * @package es.ull.flights
 */
package es.ull.flights;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.es.ull.passengers.Passenger;
import main.java.es.ull.flights.Flight;

/**
 * @class FlightsTest
 * @brief Clase de pruebas unitarias para la clase Flight.
 *
 * Esta clase contiene pruebas para verificar el correcto funcionamiento de la clase Flight,
 * incluyendo la gestión de pasajeros y la capacidad de los vuelos.
 */
class FlightsTest {

    /**
     * @brief Prueba para verificar el número de vuelo.
     *
     * Comprueba que el número de vuelo se inicialice correctamente.
     */
    @Test
    void testValidFlightNumber() {
        Flight flight = new Flight("AA1234", 100);
        assertEquals("AA1234", flight.getFlightNumber());
    }

    /**
     * @brief Prueba para añadir un pasajero a un vuelo.
     *
     * Verifica que un pasajero se pueda añadir correctamente al vuelo.
     */
    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para añadir un pasajero a un vuelo lleno.
     *
     * Verifica que se lance una excepción al intentar añadir un pasajero a un vuelo
     * sin asientos disponibles.
     */
    @Test
    void testAddPassengerToFullFlight() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        assertTrue(flight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

    /**
     * @brief Prueba para eliminar un pasajero de un vuelo.
     *
     * Verifica que un pasajero se elimine correctamente del vuelo.
     */
    @Test
    void testRemovePassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para eliminar un pasajero que no está en el vuelo.
     *
     * Verifica que no se pueda eliminar un pasajero que no está en el vuelo.
     */
    @Test
    void testRemoveNonExistentPassenger() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertFalse(flight.removePassenger(passenger));
    }

    /**
     * @brief Prueba para eliminar un pasajero de un vuelo lleno.
     *
     * Verifica que se pueda eliminar un pasajero de un vuelo lleno,
     * reduciendo el número de pasajeros.
     */
    @Test
    void testRemovePassengerFromFullFlight() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para comprobar el número de pasajeros.
     *
     * Verifica que el número de pasajeros sea correcto después de añadirlos al vuelo.
     */
    @Test
    void testNumberOfPassengers() {
        Flight flight = new Flight("AA1234", 100);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        assertEquals(2, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para inicializar un vuelo.
     *
     * Verifica que el número de vuelo y el número inicial de pasajeros se inicialicen correctamente.
     */
    @Test
    void testInitializeFlight() {
        Flight flight = new Flight("AA1234", 100);
        assertEquals("AA1234", flight.getFlightNumber());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para comprobar el número de pasajeros después de eliminar uno.
     *
     * Verifica que el número de pasajeros sea correcto después de eliminar un pasajero del vuelo.
     */
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

    /**
     * @brief Prueba para añadir un pasajero con un código de país inválido.
     *
     * Verifica que se lance una excepción al intentar crear un pasajero con un código de país inválido.
     */
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("12345678A", "John Doe", "XX"));
    }

    /**
     * @brief Prueba para añadir un pasajero a un vuelo sin suficientes asientos.
     *
     * Verifica que se lance una excepción cuando no hay suficientes asientos para añadir un pasajero.
     */
    @Test
    void testNotEnoughSeats() {
        Flight flight = new Flight("AA1234", 1);
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        flight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

}
