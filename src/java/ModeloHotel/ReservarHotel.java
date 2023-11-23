/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloHotel;

/**
 *
 * @author enriq
 */
public class ReservarHotel {
    String nombreCliente;
    String fechaInicio;
    String fechaFin;
    int numeroHabitaciones;
    double costoReserva;
    private static final double costoHabi = 100.0;
    private static int conReserva=0;
    private  String codigoreserva;
    private boolean cancelar;

    public ReservarHotel() {
    }

    public ReservarHotel(String nombreCliente, String fechaInicio, String fechaFin, int numeroHabitaciones) {
        this.nombreCliente = nombreCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroHabitaciones = numeroHabitaciones;       
         this.costoReserva = calcularCostoReserva();
         this.codigoreserva=generarCodigoReserva();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public String getCodigoreserva() {
        return codigoreserva;
    }

    public void setCodigoreserva(String codigoreserva) {
        this.codigoreserva = codigoreserva;
    }
    
    private String generarCodigoReserva() {
        conReserva++;
       return "R"+  conReserva;
    }
    private double calcularCostoReserva() {
        return numeroHabitaciones * costoHabi;
    }
}
