/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloInicioSesion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enriq
 */
public class GenerarUsuarioId {
    
    
    //EN ESTA CLASE LO QUE SE HACE ES GENERAR UN ID PARA EL CLIENTE AL MOMENTO DE REGISTRAR YA QUE POR SI MISMO NO SE PUEDE ASIGNAR
    List<Cliente> clientes;
    private int idautoincremental;

    
     public GenerarUsuarioId() {
        clientes = new ArrayList<>();
        idautoincremental = 1;
    }
    
    //GETTER Y SETTER DEL ARRAY PARA ACCCEDER DE LA CLASE TARJETA DE CREDITO

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
   ///   
    

    public Cliente generarClienteConID(String username, String password) {
        int nuevoID = idautoincremental;
        idautoincremental++;

        Cliente cli = new Cliente(nuevoID, username, password);
        clientes.add(cli);

        return cli;
    }

    public boolean existeID(int id) {
        for (Cliente cli : clientes) {
            if (cli.getIdCliente() == id) {
                System.out.println("Id existente");
                return true;
            }
        }
        System.out.println("Id no existente");
        return false;
    }
    
    public boolean existeUsuario(String usuario) {
        for (Cliente cliente : clientes) {
            if (cliente.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }
    
     public Cliente buscarCliente(String usuario, String contraseña) {
        for (Cliente cliente : clientes) {
            if (cliente.getUsuario().equals(usuario) && cliente.getContrase().equals(contraseña)) {
                return cliente;
            }
        }
        return null;
    }
    
    
    
}
