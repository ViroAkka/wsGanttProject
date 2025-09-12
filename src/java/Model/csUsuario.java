package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class csUsuario {
    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public csUsuario() {
        this.con = null;
        this.stm = null;
    }
    
    // INSERT
    public int insertUsuario(int idEmpresa, String nombre, String apellido, String email, String contraseña_hash) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("INSERT INTO Usuario " + 
                                          "(idEmpresa, nombre, apellido, email, contraseña_hash) " +
                                          "VALUES ('" + idEmpresa +"', '" +
                                          nombre +"', '" +
                                          apellido + "', '" + 
                                          email + "', '" + 
                                          contraseña_hash + "');");
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al insertar Usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // UPDATE
    public int updateUsuario(int idUsuario, int idEmpresa, String nombre, String apellido, String email, String contraseña_hash) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("UPDATE Usuario " + 
                                          "SET idEmpresa = " + idEmpresa + ", " +
                                              "nombre = '" + nombre + "', " +   
                                              "apellido = '" + apellido + "', " +
                                              "email = '" + email + "', " + 
                                              "contraseña_hash = '" + contraseña_hash + "' " + 
                                          "WHERE idUsuario = " + idUsuario + ";");
                                          
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al actualizar Usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // DELETE
    public int deleteUsuario(int idUsuario) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("DELETE Usuario " + 
                                          "WHERE idUsuario = " + idUsuario + ";");
                                          
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al eliminar Usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // SELECT
    public ArrayList<Usuario> listarUsuario() 
    {
        Usuario usuario = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Usuario;");
            
            while(rs.next())
            {
                usuario = new Usuario(rs.getInt(1), 
                                      rs.getInt("idEmpresa"), 
                                      rs.getString("nombre"), 
                                      rs.getString("apellido"), 
                                      rs.getString("email"), 
                                      rs.getString("contraseña_hash"));
                
                usuarios.add(usuario);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return usuarios;
    }
    
    // SELECT POR ID
    public Usuario listarUsuarioPorID(int idUsuario) 
    {
        Usuario usuario = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Usuario WHERE idUsuario = " + idUsuario + ";");
            
            while(rs.next())
            {
                usuario = new Usuario(rs.getInt(1), 
                                     rs.getInt("idEmpresa"), 
                                     rs.getString("nombre"), 
                                     rs.getString("apellido"), 
                                     rs.getString("email"), 
                                     rs.getString("contraseña_hash"));
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Usuario por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return usuario;
    }
    
    // AUTENTIFICACIÓN
    public Usuario autenticateUsuario(String email, String contraseña) 
    {
        Usuario usuario = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Usuario WHERE email = '" + email + "' AND contraseña_hash = '" + contraseña + "';");
            
            while(rs.next())
            {
                usuario = new Usuario(rs.getInt(1), 
                                     rs.getInt("idEmpresa"), 
                                     rs.getString("nombre"), 
                                     rs.getString("apellido"), 
                                     rs.getString("email"), 
                                     rs.getString("contraseña_hash"));
            }
            
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al autenticar Usuario por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return usuario;
    }
}
