/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

/**
 * @package main.java.es.ull.passengers
 */
package main.java.es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import main.java.es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Representa un pasajero que tiene un identificador, nombre, código de país y está asociado a un vuelo.
 *
 * Esta clase permite gestionar la información básica de un pasajero y su asociación con un vuelo.
 */
public class Passenger {

    private String identifier; ///< Identificador único del pasajero.
    private String name; ///< Nombre del pasajero.
    private String countryCode; ///< Código de país del pasajero en formato ISO 3166.
    private Flight flight; ///< Vuelo asociado al pasajero.

    /**
     * @brief Constructor de la clase Passenger.
     *
     * Inicializa un pasajero con el identificador, nombre y código de país especificados.
     *
     * @param identifier Identificador único del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código de país del pasajero en formato ISO 3166.
     *
     * @throws RuntimeException Si el código de país no es válido según la lista ISO.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador del pasajero.
     * @return Una cadena con el identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     * @return Una cadena con el nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código de país del pasajero.
     * @return Una cadena con el código de país del pasajero.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo al que está asociado el pasajero.
     * @return El vuelo actual del pasajero o `null` si no está en ningún vuelo.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asocia al pasajero con un vuelo.
     *
     * Si el pasajero ya está en un vuelo, se le elimina de ese vuelo antes de asociarlo con el nuevo.
     *
     * @param flight El vuelo al que se quiere asociar el pasajero.
     *
     * @throws RuntimeException Si no se puede eliminar del vuelo anterior o no se puede añadir al nuevo vuelo.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Establece el vuelo del pasajero.
     *
     * @param flight El vuelo al que se quiere asociar el pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Convierte la información del pasajero a una representación en cadena.
     * @return Una cadena que representa al pasajero, incluyendo su nombre, identificador y código de país.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
