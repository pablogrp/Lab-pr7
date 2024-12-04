/**
 * @package es.ull.passengers
 */
package es.ull.passengers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.es.ull.passengers.Passenger;
import main.java.es.ull.flights.Flight;

/**
 * @class PassengersTest
 * @brief Clase de pruebas unitarias para la clase Passenger.
 *
 * Esta clase contiene una serie de pruebas para verificar el correcto funcionamiento 
 * de la clase Passenger, incluyendo la inicialización, métodos de acceso y comportamiento 
 * al unirse a vuelos.
 */
class PassengersTest {

    /**
     * @brief Prueba para inicializar un pasajero con datos válidos.
     *
     * Crea una instancia de Passenger y verifica que los atributos se inicialicen correctamente.
     */
    @Test
    void testValidPassenger() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("12345678A", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("ES", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para comprobar el método getIdentifier.
     *
     * Verifica que el identificador del pasajero sea el esperado.
     */
    @Test
    void testGetIdentifier() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("12345678A", passenger.getIdentifier());
    }

    /**
     * @brief Prueba para comprobar el método getName.
     *
     * Verifica que el nombre del pasajero sea el esperado.
     */
    @Test
    void testGetName() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("John Doe", passenger.getName());
    }

    /**
     * @brief Prueba para comprobar el método getCountryCode.
     *
     * Verifica que el código de país del pasajero sea el esperado.
     */
    @Test
    void testGetCountryCode() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("ES", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para comprobar el método getFlight.
     *
     * Verifica que el pasajero esté asociado al vuelo correcto después de unirse a él.
     */
    @Test
    void testGetFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    /**
     * @brief Prueba para comprobar el método joinFlight.
     *
     * Verifica que al unirse a un vuelo, el pasajero esté asociado correctamente 
     * y el número de pasajeros en el vuelo aumente.
     */
    @Test
    void testJoinFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para comprobar el método joinFlight cuando el vuelo está lleno.
     *
     * Verifica que se lance una excepción al intentar que un pasajero se una a un vuelo 
     * que ya ha alcanzado su capacidad máxima.
     */
    @Test
    void testJoinFullFlight() {
        Passenger passenger1 = new Passenger("12345678A", "John Doe", "ES");
        Passenger passenger2 = new Passenger("87654321B", "Jane Doe", "ES");
        Flight flight = new Flight("AA1234", 1);
        passenger1.joinFlight(flight);
        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight));
    }

    /**
     * @brief Prueba para comprobar el método setFlight.
     *
     * Verifica que el método setFlight asigne correctamente un vuelo al pasajero.
     */
    @Test
    void testSetFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.setFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    /**
     * @brief Prueba para comprobar el método toString.
     *
     * Verifica que el método toString devuelva la representación correcta del pasajero.
     */
    @Test
    void testToString() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertEquals("Passenger John Doe with identifier: 12345678A from ES", passenger.toString());
    }

    // Tests para comprobar los fallos

    /**
     * @brief Prueba para comprobar un fallo en el constructor con código de país inválido.
     *
     * Verifica que se lance una excepción al intentar crear un pasajero con un código de país inválido.
     */
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("12345678A", "John Doe", "XX"));
    }

    /**
     * @brief Prueba para comprobar un fallo en el método getIdentifier.
     *
     * Verifica que el método getIdentifier no devuelva un identificador incorrecto.
     */
    @Test
    void testGetIdentifierFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("12345678B", passenger.getIdentifier());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método getName.
     *
     * Verifica que el método getName no devuelva un nombre incorrecto.
     */
    @Test
    void testGetNameFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("Jane Doe", passenger.getName());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método getCountryCode.
     *
     * Verifica que el método getCountryCode no devuelva un código de país incorrecto.
     */
    @Test
    void testGetCountryCodeFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("FR", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método getFlight.
     *
     * Verifica que el método getFlight no devuelva nulo después de unirse a un vuelo.
     */
    @Test
    void testGetFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertNotEquals(null, passenger.getFlight());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método joinFlight.
     *
     * Verifica que el método joinFlight no deje al pasajero sin vuelo y que el número 
     * de pasajeros en el vuelo no sea cero después de unirse.
     */
    @Test
    void testJoinFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.joinFlight(flight);
        assertNotEquals(null, passenger.getFlight());
        assertNotEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método setFlight.
     *
     * Verifica que el método setFlight no deje al pasajero sin vuelo después de asignar uno.
     */
    @Test
    void testSetFlightFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight = new Flight("AA1234", 100);
        passenger.setFlight(flight);
        assertNotEquals(null, passenger.getFlight());
    }

    /**
     * @brief Prueba para comprobar un fallo en el método toString.
     *
     * Verifica que el método toString no devuelva una representación incorrecta del pasajero.
     */
    @Test
    void testToStringFail() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        assertNotEquals("Passenger Jane Doe with identifier: 12345678A from ES", passenger.toString());
    }

    /**
     * @brief Prueba para comprobar el historial de vuelos anteriores del pasajero.
     *
     * Verifica que al unirse a un nuevo vuelo, el pasajero se desvincule del vuelo anterior.
     */
    @Test
    void testPreviousFlight() {
        Passenger passenger = new Passenger("12345678A", "John Doe", "ES");
        Flight flight1 = new Flight("AA1234", 100);
        Flight flight2 = new Flight("BB1234", 100);
        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);
        assertEquals(0, flight1.getNumberOfPassengers());
    }

}
