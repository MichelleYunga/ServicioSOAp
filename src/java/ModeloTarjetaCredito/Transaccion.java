/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTarjetaCredito;

/**
 *
 * @author enriq
 */
public class Transaccion {
    private String descripcion;
    private double monto;

    public Transaccion(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    

   

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }
}
