/**
 * @package main.java.es.ull.flights
 */
package main.java.es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Representa un vuelo con un número de vuelo, capacidad y una lista de pasajeros.
 *
 * La clase gestiona la información básica de un vuelo, incluyendo su número,
 * capacidad de asientos, y las operaciones de añadir y eliminar pasajeros.
 */
public class Flight {

    private String flightNumber; ///< Número único del vuelo.
    private int seats; ///< Capacidad máxima de asientos en el vuelo.
    private Set<Passenger> passengers = new HashSet<>(); ///< Conjunto de pasajeros en el vuelo.

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; ///< Expresión regular para validar números de vuelo.
    private static Pattern pattern = Pattern.compile(flightNumberRegex); ///< Patrón compilado para validar números de vuelo.

    /**
     * @brief Constructor de la clase Flight.
     *
     * Inicializa un vuelo con el número y la capacidad especificados.
     *
     * @param flightNumber El número de vuelo, que debe cumplir el formato "AA1234".
     * @param seats La cantidad de asientos disponibles en el vuelo.
     *
     * @throws RuntimeException Si el número de vuelo no es válido.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Obtiene el número de vuelo.
     * @return Una cadena con el número del vuelo.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Obtiene el número actual de pasajeros en el vuelo.
     * @return Un entero que representa el número de pasajeros en el vuelo.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Añade un pasajero al vuelo.
     *
     * Si el vuelo está lleno, lanza una excepción. Asocia el pasajero al vuelo actual.
     *
     * @param passenger El pasajero que se quiere añadir.
     * @return `true` si el pasajero se añadió correctamente, `false` si ya estaba en el vuelo.
     *
     * @throws RuntimeException Si no hay asientos disponibles en el vuelo.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Elimina un pasajero del vuelo.
     *
     * Desasocia el pasajero del vuelo actual y lo elimina de la lista de pasajeros.
     *
     * @param passenger El pasajero que se quiere eliminar.
     * @return `true` si el pasajero se eliminó correctamente, `false` si no estaba en el vuelo.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
