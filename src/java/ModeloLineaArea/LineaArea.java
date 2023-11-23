/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloLineaArea;

import java.util.List;

/**
 *
 * @author enriq
 */
public class LineaArea {
    private int idLineaAerea;
    private String nombre;
    private List<HorarioVuelo> horarios;

    public LineaArea() {
    }

    public LineaArea(int idLineaAerea, String nombre, List<HorarioVuelo> horarios) {
        this.idLineaAerea = idLineaAerea;
        this.nombre = nombre;
        this.horarios = horarios;
    }
    
    

    public int getIdLineaAerea() {
        return idLineaAerea;
    }

    public void setIdLineaAerea(int idLineaAerea) {
        this.idLineaAerea = idLineaAerea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<HorarioVuelo> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioVuelo> horarios) {
        this.horarios = horarios;
    }
}
