/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioLineaAreaWS;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import ModeloLineaArea.LineaArea;
import ModeloLineaArea.HorarioVuelo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author enriq
 */
@WebService(serviceName = "ServicioLineaArea")
public class ServicioLineaArea {

    /**
     * This is a sample web service operation
     */
    
    //ARRAYS
    private List<LineaArea> lineasAereas;
    private List<HorarioVuelo> horarios;
    
    
    
    @WebMethod(operationName = "RegistroHorario")
    public boolean RegistroHorario(@WebParam(name = "id") String idhorario, @WebParam(name = "fecha") Date fecha, @WebParam(name = "horaSalida") String horaSalida, @WebParam(name = "horaLLegada") String horaLlegada) {
        HorarioVuelo horav = new HorarioVuelo(idhorario, fecha, horaSalida, horaLlegada);
        horarios.add(horav);
        return true;
    }

    @WebMethod(operationName = "RegistroLineaArea")
    public boolean Registro(@WebParam(name = "id") int idLinea, @WebParam(name = "nombre") String nombre, List<HorarioVuelo> horarios) {
        for (LineaArea li : lineasAereas) {
            if (nombre.equals(li.getNombre())) {
                return false;//Registro Areo
            }
        }
        lineasAereas.add(new LineaArea(idLinea, nombre, horarios));
        return true;//Registro Areo
    }

    //CONSULTAR HORARIOS
    @WebMethod(operationName = "BuscarLineaArea")
    public LineaArea buscarLineaArea(@WebParam(name = "nombre") String nombre) {
        for (LineaArea lineaA : lineasAereas) {
            if (nombre.equals(lineaA.getNombre())) {
                List<HorarioVuelo> horarios = lineaA.getHorarios();// Obtener los horarios de vuelo de la línea aérea
                lineaA.setHorarios(horarios);
                return lineaA;
            }
        }
        return null;
    }
    
    //BUSCAR VUELOS DISPONIBLES EN BASE A FECHA Y HORA
    @WebMethod(operationName = "BuscarVuelos")
    public List<HorarioVuelo> buscarVuelosDisponibles( @WebParam(name = "fecha") Date fecha,@WebParam(name = "hora") String hora){
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaString = formatoFecha.format(fecha);
        
        if(!esFechaValida(fechaString) || !esHoravalida(hora)){
            return new ArrayList<>();
        }
        
        Date fechaParametro = convertirfecha(fechaString);
        
        
        
        List<HorarioVuelo> vuelosDisponibles = new ArrayList<>();
        
        for(HorarioVuelo vuelos : horarios){
            if (vuelos.cocidenciaFechaHora(hora, hora)) {
                vuelosDisponibles.add(vuelos);
            }
        }
        
        return vuelosDisponibles;
                               
    }
    
    @WebMethod (operationName = "CambiarVuelo")
    public boolean cambiarVuelo(@WebParam(name = "numeroVuelo") int idVuelo,
            @WebParam(name = "nuevaFecha") String nuevaFecha,
            @WebParam(name = "nuevaHora") String nuevaHora){
        
        if(!esFechaValida(nuevaFecha) || !esHoravalida(nuevaHora)){
            return false;
        }
        
        for(HorarioVuelo vuelo : horarios){
            
            if(vuelo.getIdHorario().equals(vuelo)){
                
                vuelo.setFecha(convertirfecha(nuevaFecha));
                vuelo.setHoraSalida(nuevaFecha);
                return true;
            }
        }
        
        return false;
        
    }
    
    @WebMethod (operationName = "AnularVuelo")
    public Boolean anularVuelo(@WebParam(name = "numeroVuelo") String numeroVuelo){
        
        for(HorarioVuelo vuelo : horarios){
            if(vuelo.getIdHorario().equals(numeroVuelo)){
                horarios.remove(vuelo);
                return true;
            }
        }
        return false;
    }
    
    
    
    //VALIDACIONES
    private boolean esFechaValida(String fecha){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    private boolean esHoravalida(String hora){
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false);
            timeFormat.parse(hora);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    private Date convertirfecha(String fecha){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("ERROR");
            return null;
        }
    }


}
