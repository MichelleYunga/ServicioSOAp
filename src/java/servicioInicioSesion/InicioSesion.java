/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioInicioSesion;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modeloInicioSesion.Cliente;
import modeloInicioSesion.GenerarUsuarioId;

/**
 *
 * @author enriq
 */
@WebService(serviceName = "InicioSesion")
public class InicioSesion {

    /**
     * This is a sample web service operation
     */
    private GenerarUsuarioId generar;

    @WebMethod(operationName = "login")
    public Cliente login(@WebParam(name = "username") String usuario, @WebParam(name = "password") String contraseña) {
        Cliente cliente = generar.buscarCliente(usuario, contraseña);
        if (cliente != null) {
            System.out.println("Credenciales correctas");
            return cliente;
        }
        System.out.println("Sus credenciales sin incorrectas");
        return null;
    }

    @WebMethod(operationName = "Regístrese")
    public boolean registrese(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula,
            @WebParam(name = "usuario") String usuario, @WebParam(name = "contraseña") String contra, @WebParam(name = "contraseña1") String contra1) {
        if (generar.existeUsuario(usuario)) {
            System.out.println("No se puede registrar, usuario ya existente");
            return false;
        }
        if (!compararContraseñas(contra,contra1)) {
            System.out.println("Las contraseñas no coinciden");
            return false;
        }

        Cliente nuevoCliente = generar.generarClienteConID(nombre, apellido, cedula, usuario, contra, contra1);
        System.out.println("Usuario creado exitosamente. : " + nuevoCliente.getUsuario());
        return true;
    }

    //METRODO PARA VALIDAR LAS CONTRASEÑAS
    private boolean compararContraseñas(String contraseña1, String contraseña2) {
        return contraseña1.equals(contraseña2);
    }
}
