package Servicio;

import Model.Proyecto;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Model.csProyecto;

import java.time.LocalDate;
import java.util.ArrayList;

@WebService(serviceName = "srvProyecto")
public class srvProyecto {

    @WebMethod(operationName = "insertProyecto")
    public int insertProyecto(@WebParam(name = "idEmpresa") int idEmpresa, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csProyecto proyecto = new csProyecto();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return proyecto.insertProyecto(idEmpresa, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "updateProyecto")
    public int updateProyecto(@WebParam(name = "idProyecto") int idProyecto,
                             @WebParam(name = "idEmpresa") int idEmpresa, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion,
                             @WebParam(name = "fechaInicio") String fechaInicioStr,
                             @WebParam(name = "fechaFinalizacion") String fechaFinalizacionStr) {
        
        csProyecto proyecto = new csProyecto();
        
        LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) 
        ? LocalDate.parse(fechaInicioStr) 
        : null;

        LocalDate fechaFinalizacion = (fechaFinalizacionStr != null && !fechaFinalizacionStr.isEmpty()) 
        ? LocalDate.parse(fechaFinalizacionStr) 
        : null;
        
        return proyecto.updateProyecto(idProyecto, idEmpresa, nombre, descripcion, fechaInicio, fechaFinalizacion);
    }
    
    @WebMethod(operationName = "deleteProyecto")
    public int deleteProyecto(@WebParam(name = "idProyecto") int idProyecto) {
        
        csProyecto proyecto = new csProyecto();
        
        return proyecto.deleteProyecto(idProyecto);
    }
    
    @WebMethod(operationName = "listarProyecto")
    public ArrayList<Proyecto> listarProyecto() {
        csProyecto proyecto = new csProyecto();
        
        return proyecto.listarProyecto();
    }
    
    @WebMethod(operationName = "listarProyectoPorID")
    public Proyecto listarProyectoPorID(@WebParam(name = "idProyecto") int idProyecto) {
        csProyecto proyecto = new csProyecto();
        
        return proyecto.listarProyectoPorID(idProyecto);
    }
}           
