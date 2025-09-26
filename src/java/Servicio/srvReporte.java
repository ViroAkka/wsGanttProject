
package Servicio;

import Model.Actividad;
import Model.Tarea;
import Model.csReporte;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "srvReporte")
public class srvReporte {

    @WebMethod(operationName = "listarTareasPorProyecto")
    public ArrayList<Tarea> listarTareasPorProyecto(@WebParam(name = "idProyecto")int idProyecto) {
        csReporte reporteTareas = new csReporte();
        
        return reporteTareas.listarTareasPorProyecto(idProyecto);
    }
    
    @WebMethod(operationName = "listarActividadesPorTarea")
    public ArrayList<Actividad> listarActividadesPorTarea(@WebParam(name = "idTarea")int idTarea) {
        csReporte reporteActividades = new csReporte();
        
        return reporteActividades.listarActividadesPorTarea(idTarea);
    }
}
