package Servicio;

import Model.Usuario;
import Model.csUsuario;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "srvUsuario")
public class srvUsuario {

    @WebMethod(operationName = "insertUsuario")
    public int insertUsuario(@WebParam(name = "idEmpresa") int idEmpresa, 
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "apellido") String apellido, 
                             @WebParam(name = "email") String email,
                             @WebParam(name = "contraseña_hash") String contraseña_hash) {
        
        csUsuario usuario = new csUsuario();
        
        return usuario.insertUsuario(idEmpresa, nombre, apellido, email, contraseña_hash);
    }
    
    @WebMethod(operationName = "updateUsuario")
    public int updateUsuario(@WebParam(name = "idUsuario") int idUsuario,
                             @WebParam(name = "idEmpresa") int idEmpresa,
                             @WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "apellido") String apellido, 
                             @WebParam(name = "email") String email,
                             @WebParam(name = "contraseña_hash") String contraseña_hash) {
        
        csUsuario usuario = new csUsuario();
        
        return usuario.updateUsuario(idUsuario, idEmpresa, nombre, apellido, email, contraseña_hash);
    }
    
    @WebMethod(operationName = "deleteUsuario")
    public int deleteUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
        csUsuario usuario = new csUsuario();
        
        return usuario.deleteUsuario(idUsuario);
    }
    
    @WebMethod(operationName = "listarUsuario")
    public ArrayList<Usuario> listarUsuario() {
        csUsuario usuario = new csUsuario();
        
        return usuario.listarUsuario();
    }
    
    @WebMethod(operationName = "listarUsuarioPorID")
    public Usuario listarUsuarioPorID(@WebParam(name = "idUsuario") int idUsuario) {
        csUsuario usuario = new csUsuario();
        
        return usuario.listarUsuarioPorID(idUsuario);
    }
    
    @WebMethod(operationName = "auntenticateUsuario")
    public Usuario auntenticateUsuario(@WebParam(name = "email") String email,
                                      @WebParam(name = "contraseña") String contraseña) {
        csUsuario usuario = new csUsuario();
        
        return usuario.autenticateUsuario(email, contraseña);
    }
}
