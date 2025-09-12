package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class csProyecto {
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    public csProyecto() {
        this.con = null;
        this.stm = null;
    }
    
    // INSERT
    public int insertProyecto(int idEmpresa, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("INSERT INTO Proyecto " + 
                                          "(idEmpresa, nombre, descripcion, fechaInicio, fechaFinalizacion) " +
                                          "VALUES ('" + idEmpresa +"', '" +
                                          nombre +"', '" +
                                          descripcion + "', '" + 
                                          java.sql.Date.valueOf(fechaInicio) + "', '" + 
                                          java.sql.Date.valueOf(fechaFinalizacion) + "');");
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al insertar Proyecto: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // UPDATE
    public int updateProyecto(int idProyecto, int idEmpresa, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("UPDATE Proyecto " + 
                                          "SET idEmpresa = '" + idEmpresa + "', " +
                                              "nombre = '" + nombre +"', " +
                                              "descripcion = '" + descripcion + "', " +
                                              "fechaInicio = '" + java.sql.Date.valueOf(fechaInicio) + "', " +
                                              "fechaFinalizacion = '" + java.sql.Date.valueOf(fechaFinalizacion) + "' " + 
                                          "WHERE idProyecto = " + idProyecto + ";");
                                          
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al actualizar Proyecto: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // DELETE
    public int deleteProyecto(int idProyecto) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("DELETE Proyecto " + 
                                          "WHERE idProyecto = " + idProyecto + ";");
                                          
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al eliminar Proyecto: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // SELECT
    public ArrayList<Proyecto> listarProyecto() 
    {
        Proyecto proyecto = null;
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Proyecto;");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                proyecto = new Proyecto(rs.getInt(1), 
                                        rs.getInt("idEmpresa"), 
                                        rs.getString("nombre"), 
                                        rs.getString("descripcion"), 
                                        (sqlFechaInicio != null) ? new java.util.Date(sqlFechaInicio.getTime()) : null,
                                        (sqlFechaFinal != null) ? new java.util.Date(sqlFechaFinal.getTime()) : null);
                
                proyectos.add(proyecto);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Proyecto: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return proyectos;
    }
    
    // SELECT POR ID
    public Proyecto listarProyectoPorID(int idProyecto) 
    {
        Proyecto proyecto = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Proyecto WHERE idProyecto = " + idProyecto + ";");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");

                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                proyecto = new Proyecto(rs.getInt(1), 
                                        rs.getInt("idEmpresa"), 
                                        rs.getString("nombre"), 
                                        rs.getString("descripcion"), 
                                        (sqlFechaInicio != null) ? new java.util.Date(sqlFechaInicio.getTime()) : null,
                                        (sqlFechaFinal != null) ? new java.util.Date(sqlFechaFinal.getTime()) : null);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Proyecto por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return proyecto;
    }
}
