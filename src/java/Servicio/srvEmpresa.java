package Servicio;

import Model.Empresa;
import Model.csEmpresa;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "srvEmpresa")
public class srvEmpresa {

    @WebMethod(operationName = "insertEmpresa")
    public int insertEmpresa(@WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "direccion") String direccion, 
                             @WebParam(name = "representanteLegal") String representanteLegal,
                             @WebParam(name = "tipoEmpresa") String tipoEmpresa) {
        
        csEmpresa empresa = new csEmpresa();
        
        return empresa.insertEmpresa(nombre, representanteLegal, direccion, tipoEmpresa);
    }
    
    @WebMethod(operationName = "updateEmpresa")
    public int updateEmpresa(@WebParam(name = "idEmpresa") int idEmpresa,
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "direccion") String direccion, 
                             @WebParam(name = "representanteLegal") String representanteLegal,
                             @WebParam(name = "tipoEmpresa") String tipoEmpresa) {
        
        csEmpresa empresa = new csEmpresa();
        
        return empresa.updateEmpresa(idEmpresa, nombre, representanteLegal, direccion, tipoEmpresa);
    }
    
    @WebMethod(operationName = "deleteEmpresa")
    public int deleteEmpresa(@WebParam(name = "idEmpresa") int idEmpresa) {
        
        csEmpresa empresa = new csEmpresa();
        
        return empresa.deleteEmpresa(idEmpresa);
    }
    
    @WebMethod(operationName = "listarEmpresa")
    public ArrayList<Empresa> listarEmpresa() {
        csEmpresa empresa = new csEmpresa();
        
        return empresa.listarEmpresa();
    }
    
    @WebMethod(operationName = "listarEmpresaPorID")
    public Empresa listarEmpresaPorID(@WebParam(name = "idEmpresa") int idEmpresa) {
        csEmpresa empresa = new csEmpresa();
        
        return empresa.listarEmpresaPorID(idEmpresa);
    }
}
