<<<<<<< Updated upstream
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTarjetaCredito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modeloInicioSesion.GenerarUsuarioId;
import modeloInicioSesion.Cliente;

/**
 *
 * @author enriq
 */
public class TarjetaCredito {

    private int idCliente;
    private String numero;
    private String titular;
    private String fechaVencimiento;
    private String codigoSeguridad;
    //Este atributo sirve para el ingreso de dinero al momento de registrar la tarjeta
    private float saldoDisponible;
    private List<Transaccion> historialTransacciones;

    public TarjetaCredito(String numero, String titular, String fechaVencimiento, String codigoSeguridad, float saldoDisponible) {
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.saldoDisponible = saldoDisponible;
        this.idCliente = asignarIdCliente();
    }

    public TarjetaCredito(int idCliente, String numero, String titular, String fechaVencimiento, String codigoSeguridad, float saldoDisponible) {
        this.idCliente = asignarIdCliente();
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.saldoDisponible = saldoDisponible;
        this.historialTransacciones = new ArrayList<>();
    }

   
    

    public TarjetaCredito() {
    }
    
//METODO PARA TRAER EL ID DEL ULTIMO REGISTRO DEL CLIENTE
    public int asignarIdCliente() {
        GenerarUsuarioId generador = new GenerarUsuarioId();
        //Obetenemos la lista de clientes
        List<Cliente> Listaclientes = generador.getClientes();
        //convertimos a un array
        ArrayList<Cliente> clientes = new ArrayList<>(Listaclientes);

        if (!clientes.isEmpty()) {
            Cliente ultimoCliente = clientes.get(clientes.size() - 1);
            return ultimoCliente.getIdCliente();
        }

        return 0; //No hay cliente registradoo
    }
    
    

    public TarjetaCredito(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public float getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(float saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
       public void agregarTransaccion(String descripcion, double monto, Date fecha) {
        this.historialTransacciones.add(new Transaccion(descripcion, monto, fecha));
    }
       
    public List<Transaccion> getHistorialTransacciones() {
        return this.historialTransacciones;
    }

}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTarjetaCredito;

import java.util.ArrayList;
import java.util.List;
import modeloInicioSesion.GenerarUsuarioId;
import modeloInicioSesion.Cliente;

/**
 *
 * @author enriq
 */
public class TarjetaCredito {

    private int idCliente;
    private String numero;
    private String titular;
    private String fechaVencimiento;
    private String codigoSeguridad;
    //Este atributo sirve para el ingreso de dinero al momento de registrar la tarjeta
    private float saldoDisponible;
    private List<Transaccion> historialTransacciones;

    public TarjetaCredito(String numero, String titular, String fechaVencimiento, String codigoSeguridad, float saldoDisponible) {
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.saldoDisponible = saldoDisponible;
        this.idCliente = asignarIdCliente();
    }

    public TarjetaCredito(int idCliente, String numero, String titular, String fechaVencimiento, String codigoSeguridad, float saldoDisponible) {
        this.idCliente = asignarIdCliente();
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.saldoDisponible = saldoDisponible;
    }  
    

    public TarjetaCredito() {
    }
    
//METODO PARA TRAER EL ID DEL ULTIMO REGISTRO DEL CLIENTE
    public int asignarIdCliente() {
        GenerarUsuarioId generador = new GenerarUsuarioId();
        //Obetenemos la lista de clientes
        List<Cliente> Listaclientes = generador.getClientes();
        //convertimos a un array
        ArrayList<Cliente> clientes = new ArrayList<>(Listaclientes);

        if (!clientes.isEmpty()) {
            Cliente ultimoCliente = clientes.get(clientes.size() - 1);
            return ultimoCliente.getIdCliente();
        }

        return 0; //No hay cliente registradoo
    }
    
    
    //METODOS PARA LAS TRANSACCIONES
    public void agregarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }

    public List<Transaccion> obtenerHistorialTransacciones() {
        return historialTransacciones;
    }
    

    public TarjetaCredito(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public float getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(float saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    

}
>>>>>>> Stashed changes
