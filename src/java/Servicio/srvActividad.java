package Servicio;

import Model.Actividad;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Model.csActividad;

import java.time.LocalDate;
import java.util.ArrayList;

@WebService(serviceName = "srvActividad")
public class srvActividad {
    @WebMethod(operationName = "insertActividad")
    public int insertActividad(@WebParam(name = "idTarea") int idTarea, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csActividad actividad = new csActividad();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return actividad.insertActividad(idTarea, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "updateActividad")
    public int updateActividad(@WebParam(name = "idActividad") int idActividad,
                             @WebParam(name = "idTarea") int idTarea, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csActividad actividad = new csActividad();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return actividad.updateActividad(idActividad, idTarea, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "deleteActividad")
    public int deleteActividad(@WebParam(name = "idActividad") int idActividad) {
        
        csActividad actividad = new csActividad();
        
        return actividad.deleteActividad(idActividad);
    }
    
    @WebMethod(operationName = "listarActividad")
    public ArrayList<Actividad> listarActividad() {
        csActividad actividad = new csActividad();
        
        return actividad.listarActividad();
    }
    
    @WebMethod(operationName = "listarActividadPorID")
    public Actividad listarActividadPorID(@WebParam(name = "idActividad") int idActividad) {
        csActividad actividad = new csActividad();
        
        return actividad.listarActividadPorID(idActividad);
    }
}
