package Servicio;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Model.csTarea;
import Model.Tarea;
import java.time.LocalDate;
import java.util.ArrayList;

@WebService(serviceName = "srvTarea")
public class srvTarea {

    @WebMethod(operationName = "insertTarea")
    public int insertTarea(@WebParam(name = "idProyecto") int idProyecto, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csTarea tarea = new csTarea();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return tarea.insertTarea(idProyecto, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "updateTarea")
    public int updateTarea(@WebParam(name = "idTarea") int idTarea,
                             @WebParam(name = "idProyecto") int idProyecto, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csTarea tarea = new csTarea();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return tarea.updateTarea(idTarea, idProyecto, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "deleteTarea")
    public int deleteTarea(@WebParam(name = "idTarea") int idTarea) {
        
        csTarea tarea = new csTarea();
        
        return tarea.deleteTarea(idTarea);
    }
    
    @WebMethod(operationName = "listarTarea")
    public ArrayList<Tarea> listarTarea() {
        csTarea tarea = new csTarea();
        
        return tarea.listarTarea();
    }
    
    @WebMethod(operationName = "listarTareaPorID")
    public Tarea listarTareaPorID(@WebParam(name = "idTarea") int idTarea) {
        csTarea tarea = new csTarea();
        
        return tarea.listarTareaPorID(idTarea);
    }
}
