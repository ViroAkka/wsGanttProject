package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class csEmpresa {
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    public csEmpresa() {
        this.con = null;
        this.stm = null;
    }
    
    // INSERT
    public int insertEmpresa(String nombre, String representanteLegal, String direccion, String tipoEmpresa) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("INSERT INTO Empresa " + 
                                          "(nombre, representanteLegal, direccion, tipoEmpresa) " +
                                          "VALUES ('" + nombre +"', '" +
                                          representanteLegal +"', '" +
                                          direccion + "', '" + 
                                          tipoEmpresa + "');");
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al insertar Empresa: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // UPDATE
    public int updateEmpresa(int idEmpresa, String nombre, String representanteLegal, String direccion, String tipoEmpresa) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("UPDATE Empresa " + 
                                          "SET nombre = '" + nombre + "', " +
                                              "representanteLegal = '" + representanteLegal +"', " +
                                              "direccion = '" + direccion + "', " +
                                              "tipoEmpresa = '" + tipoEmpresa + "' " + 
                                          "WHERE idEmpresa = " + idEmpresa + ";");
                                          
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al actualizar Empresa: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // DELETE
    public int deleteEmpresa(int idEmpresa) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("DELETE Empresa " + 
                                          "WHERE idEmpresa = " + idEmpresa + ";");
                                          
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al eliminar Empresa: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // SELECT
    public ArrayList<Empresa> listarEmpresa() 
    {
        Empresa empresa = null;
        ArrayList<Empresa> empresas = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Empresa;");
            
            while(rs.next())
            {
                empresa = new Empresa(rs.getInt(1), 
                                      rs.getString("nombre"), 
                                      rs.getString("direccion"), 
                                      rs.getString("representanteLegal"), 
                                      rs.getString("tipoEmpresa"));
                
                empresas.add(empresa);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Empresa: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return empresas;
    }
    
    // SELECT POR ID
    public Empresa listarEmpresaPorID(int idEmpresa) 
    {
        Empresa empresa = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Empresa WHERE idEmpresa = " + idEmpresa + ";");
            
            while(rs.next())
            {
                empresa = new Empresa(rs.getInt(1), 
                                      rs.getString("nombre"), 
                                      rs.getString("direccion"), 
                                      rs.getString("representanteLegal"), 
                                      rs.getString("tipoEmpresa"));
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Empresa por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return empresa;
    }
}
