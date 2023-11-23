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
    public boolean registrese(@WebParam(name = "usuario") String usuario, @WebParam(name = "contraseña") String contraseña) {
        if (generar.existeUsuario(usuario)) {
            System.out.println("No se puede registrar, usuario ya existente");
            return false;
        }

        Cliente nuevoCliente = generar.generarClienteConID(usuario, contraseña);
        System.out.println("Usuario creado exitosamente. : " + nuevoCliente.getUsuario());
        return true;
    }

}
