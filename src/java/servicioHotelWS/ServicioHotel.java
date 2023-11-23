/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioHotelWS;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import ModeloHotel.ReservarHotel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author enriq
 */
@WebService(serviceName = "ServicioHotel")
public class ServicioHotel {

    /**
     * This is a sample web service operation
     */
     private Map<Integer, ReservarHotel> reservas;

    public ServicioHotel() {
        this.reservas = new HashMap<>();

    }

    @WebMethod
    public String reservarHotel(
            @WebParam(name = "nombreCliente") String nombreCliente,
            @WebParam(name = "fechaInicio") String fechaInicio,
            @WebParam(name = "fechaFin") String fechaFin,
            @WebParam(name = "numeroHabitaciones") Integer numeroHabitaciones) {

        if (nombreCliente == null || nombreCliente.trim().isEmpty()
                || fechaInicio == null || fechaInicio.trim().isEmpty()
                || fechaFin == null || fechaFin.trim().isEmpty()
                || numeroHabitaciones == null || numeroHabitaciones.toString().trim().isEmpty()//              
                ) {

            return "Todos los campos son obligatorios.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, formatter);
            LocalDate fechaFinDate = LocalDate.parse(fechaFin, formatter);

            if (fechaInicioDate.isAfter(fechaFinDate)) {
                return "La fecha de inicio debe ser antes de la fecha de fin.";
            }
        } catch (Exception e) {
            return "Formato de fecha inválido, utiliza Año-Mes-Día.";
        }
        if (habitacionDisponible(numeroHabitaciones)) {
            ReservarHotel reserva = new ReservarHotel(nombreCliente, fechaInicio, fechaFin, numeroHabitaciones);
            reservas.put(numeroHabitaciones, reserva);
            double costoReserv = reserva.getCostoReserva();
            String codigoReserv = reserva.getCodigoreserva();
            return "Reserva realizada: " + codigoReserv
                    + "\n:Cliente: " + nombreCliente
                    + "\nHabitaciones: " + numeroHabitaciones
                    + "\nCosto total: " + costoReserv;

        } else {
            return "La habitación " + numeroHabitaciones + " ya está reservada.";
        }

    }

    @WebMethod
    public String cancelarReserva(@WebParam(name = "codigoreserva") String codigoreserva) {
        if (codigoreserva == null || codigoreserva.trim().isEmpty()) {
            return "El codigo de reserva es obligatorio.";
        }
        for (ReservarHotel reserva : reservas.values()) {
            if (reserva.getCodigoreserva().equals(codigoreserva)) {
                reserva.setCancelar(true);
                return "Se cancelo la reserva de la habitacion: " + codigoreserva;
            }
        }
        return "No existe reserva con este codigo " + codigoreserva + ".";

    }

    private boolean habitacionDisponible(int numeroHabitacion) {
        return !reservas.containsKey(numeroHabitacion) || reservas.get(numeroHabitacion).isCancelar();
    }

    @WebMethod
    public String verificarDisponibilidad(
            @WebParam(name = "fechaInicio") String fechaInicio,
            @WebParam(name = "fechaFin") String fechaFin) {

        if (fechaInicio == null || fechaInicio.trim().isEmpty()
                || fechaFin == null || fechaFin.trim().isEmpty()) {
            return "Las fechas de inicio y fin son obligatorias.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, formatter);
            LocalDate fechaFinDate = LocalDate.parse(fechaFin, formatter);

            if (fechaInicioDate.isAfter(fechaFinDate)) {
                return "La fecha de inicio debe ser antes de la fecha de fin.";
            }

            for (Map.Entry<Integer, ReservarHotel> entry : reservas.entrySet()) {

                ReservarHotel reserva = entry.getValue();

                if (reserva != null && !reserva.isCancelar()) {
                    LocalDate inicioReserva = LocalDate.parse(reserva.getFechaInicio(), formatter);
                    LocalDate finReserva = LocalDate.parse(reserva.getFechaFin(), formatter);

                    if (!(fechaFinDate.isBefore(inicioReserva) || fechaInicioDate.isAfter(finReserva))) {
                        return "No disponible para reservar. Existe una reserva en el rango de fechas seleccionado.";
                    }
                }
            }

            return "Disponible para reservar";

        } catch (Exception e) {
            return "Formato de fecha inválido, utiliza Año-Mes-Día.";
        }
    }
@WebMethod
public String listarReservas() {
    if (reservas.isEmpty()) {
        return "No hay reservas registradas.";
    }

    StringBuilder listaReservas = new StringBuilder("Listado de Reservas:\n");

    for (Map.Entry<Integer, ReservarHotel> entry : reservas.entrySet()) {
        ReservarHotel reserva = entry.getValue();
        listaReservas.append("Cliente: ").append(reserva.getNombreCliente())
                .append(", Habitaciones: ").append(reserva.getNumeroHabitaciones())
                .append(", Fecha de inicio: ").append(reserva.getFechaInicio())
                .append(", Fecha de fin: ").append(reserva.getFechaFin())
                .append(", Costo total: ").append(reserva.getCostoReserva())
                .append(", Estado de la reserva: ").append(reserva.isCancelar() ? "Cancelada" : "Activa")
                .append("\n");
    }

    return listaReservas.toString();
}
}
