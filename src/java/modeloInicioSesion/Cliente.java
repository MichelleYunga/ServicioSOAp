/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloInicioSesion;

/**
 *
 * @author enriq
 */
public class Cliente {
    
    private int idCliente;
    private String usuario;
    private String contrase;

    public Cliente(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contrase = contraseña;
    }

    public Cliente(int idCliente, String usuario, String contraseña) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.contrase = contraseña;
    }
    
    
    public Cliente() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrase() {
        return contrase;
    }

    public void setContrase(String contraseña) {
        this.contrase = contraseña;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
}
