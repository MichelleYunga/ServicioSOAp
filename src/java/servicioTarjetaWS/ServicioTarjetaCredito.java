
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioTarjetaWS;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import ModeloTarjetaCredito.TarjetaCredito;
import ModeloTarjetaCredito.Transaccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modeloInicioSesion.Cliente;
import modeloInicioSesion.GenerarUsuarioId;

/**
 *
 * @author enriq
 */
@WebService(serviceName = "ServicioTarjetaCredito")
public class ServicioTarjetaCredito {

    /**
     * This is a sample web service operation
     */
    private List<TarjetaCredito> tarjetasCredito;
    TarjetaCredito tar;
    private List<Transaccion> historialTransacciones;

    //METODO PARA EL REGISTRO DE LA TARJETA
    @WebMethod(operationName = "RegistroTarjeta")
    public boolean Registro(@WebParam(name = "numero") String numero,
            @WebParam(name = "titular") String titular,
            @WebParam(name = "fechaVencimiento") String fechaVencimiento,
            @WebParam(name = "codigoSeguridad") String codigoSeguridad,
            @WebParam(name = "saldoDisponible") float saldoDisponible) {
        
        
         
        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (numero.equals(tarjeta.getNumero())) {
                System.out.println("Ya existe esta tarjeta de credito");
                return false; // Tarjeta ya registrada
            }
        }

        int idCliente = tar.asignarIdCliente();
        tarjetasCredito.add(new TarjetaCredito(idCliente, numero, titular, fechaVencimiento, codigoSeguridad, saldoDisponible));
        System.out.println("Tarjeta de credito creada exitosamente");
        return true; // Registro de tarjeta exitoso
    }

    //METODO PARA ACTUALIZAR LA TARJETA DE CREDITO
    @WebMethod(operationName = "ActualizarTarjeta")
    public boolean ActualizarTarjeta(@WebParam(name = "numero") String numero,
            @WebParam(name = "titular") String titular,
            @WebParam(name = "fechaVencimiento") String fechaVencimiento,
            @WebParam(name = "codigoSeguridad") String codigoSeguridad,
            @WebParam(name = "saldoDisponible") float saldoDisponible) {
        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (numero.equals(tarjeta.getNumero())) {
                tarjeta.setTitular(titular);
                tarjeta.setFechaVencimiento(fechaVencimiento);
                tarjeta.setCodigoSeguridad(codigoSeguridad);
                tarjeta.setSaldoDisponible(saldoDisponible);
                System.out.println("Tarjeta actualizada");
                return true; // Actualización exitosa
            }
        }
        System.out.println("Tarjeta no encontrada");
        return false; // Tarjeta no encontrada
    }

    //TARJETA DE CREDITO
    public boolean validarFechaVencimiento(String numeroTarjeta, String fechaVencimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaVencimientoTarjeta = LocalDate.parse(fechaVencimiento, formatter);
        LocalDate fechaActual = LocalDate.now();

        if (fechaVencimientoTarjeta.isBefore(fechaActual)) {
            // Tarjeta vencida
            return false;
        }

        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (tarjeta.getNumero().equals(numeroTarjeta) && tarjeta.getFechaVencimiento().equals(fechaVencimiento)) {
                // Fecha de vencimiento válida
                System.out.println("Fecha de vencimiento valida");
                return true;
            }
        }
        System.out.println("Fecha de vencimiento invalida o tarjeta no encontrada");
        // Fecha de vencimiento inválida o tarjeta no encontrada
        return false;
    }

    //METODO PARA CONSULTAR SALDO DISPONIBLE
    @WebMethod(operationName = "consultarSaldoDisponible")
    public Float consultarSaldoDisponible(@WebParam(name = "numero") String numeroTarjeta) {
        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (tarjeta.getNumero().equals(numeroTarjeta)) {
                return tarjeta.getSaldoDisponible();
            }
        }
        return null;
    }

    //METODO PARA REALIZAR TRANSACCIONES
    @WebMethod
    public boolean realizarTransaccion(@WebParam(name = "numeroTarjeta") String numeroTarjeta,
            @WebParam(name = "monto") double monto,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "fecha") Date fecha) {

        if (validarTarjetaCredito(numeroTarjeta) && monto > 0) {
            Transaccion transaccion = new Transaccion(descripcion, monto,fecha);
            historialTransacciones.add(transaccion);
            System.out.println("Su transferencia fue exitos");
            // Retorna verdadero si la transacción fue exitosa
            return true;
        } else {
            System.out.println("la transferencia fallo");
            // Retorna falso si la transacción falló
            return false;
        }
    }

    //METODO PARA VER EL HISTORIAL DE LA TARJETA DE CREDITO
    @WebMethod
    public List<Transaccion> obtenerHistorialTarjeta(@WebParam(name = "numeroTarjeta") String numeroTarjeta) {
        // Buscar la tarjeta de crédito en la lista de tarjetas
        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (tarjeta.getNumero().equals(numeroTarjeta)) {
                return tarjeta.getHistorialTransacciones();
            }
        }
        System.out.println("No se pudo encontrar el hitorial de la tarjeta");
        // Si no se encuentra la tarjeta, devolver una lista vacía
        return new ArrayList<>();
    }
    

    //METODO PARA RETIRAR DINERO
    @WebMethod(operationName = "retirarDinero")
    public boolean retirarDinero(@WebParam(name = "numero") String numeroTarjeta,
            @WebParam(name = "monto") float monto,
            @WebParam(name = "idCliente") int idCliente) {
        for (TarjetaCredito tarjeta : tarjetasCredito) {
            if (tarjeta.getNumero().equals(numeroTarjeta)) {
                if (tarjeta.getSaldoDisponible() >= monto) {
                    if (esClienteLegitimo(idCliente)) {
                        tarjeta.setSaldoDisponible(tarjeta.getSaldoDisponible() - monto);
                        System.out.println("El retiro de dinero fue exitoso");
                        return true; // Retiro exitoso
                    } else {
                        System.out.println("No se puede realizar el retiro, el cliente no es legítimo");
                        return false; // Cliente no legítimo
                    }
                } else {
                    System.out.println("No se puede realizar el retiro, su saldo es insuficiente");
                    return false; // Saldo insuficiente
                }
            }
        }
        System.out.println("TARJETA NO ENCONTRADA");
        return false; // Tarjeta no encontrada
    }

    //METODO PARA VER SI EL CLIENTE LEGITIMOO
    //SE VERIFICA MEDIANTE EL ID    
    private boolean esClienteLegitimo(int idCliente) {
        GenerarUsuarioId generador = new GenerarUsuarioId();
        //Obetenemos la lista de clientes
        List<Cliente> Listaclientes = generador.getClientes();
        //convertimos a un array
        ArrayList<Cliente> clientes = new ArrayList<>(Listaclientes);
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return true; // Cliente legítimo
            }
        }
        return false; // Cliente no encontrado
    }

    //VALIDACIONES
    private boolean esFechaValida(String fecha) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    //VALIDAR TARJETA
    public boolean validarTarjetaCredito(String numeroTarjeta) {
        // Eliminar espacios en blanco y guiones del número de tarjeta
        String numeroTarjetaSinEspacios = numeroTarjeta.replace(" ", "").replace("-", "");

        // Verificar que el número de tarjeta contenga solo dígitos
        if (!numeroTarjetaSinEspacios.matches("\\d+")) {
            return false;
        }

        // Aplicar el algoritmo de Luhn
        int suma = 0;
        boolean doble = false;
        for (int i = numeroTarjetaSinEspacios.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroTarjetaSinEspacios.charAt(i));
            if (doble) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
            doble = !doble;
        }

        // La tarjeta es válida si la suma es divisible por 10
        return suma % 10 == 0;
    }
}
