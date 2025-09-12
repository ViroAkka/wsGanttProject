package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class csTarea {
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    public csTarea() {
        this.con = null;
        this.stm = null;
    }
    
    // INSERT
    public int insertTarea(int idProyecto, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("INSERT INTO Tarea " + 
                                          "(idProyecto, nombre, descripcion, fechaInicio, fechaFinalizacion) " +
                                          "VALUES ('" + idProyecto +"', '" +
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
            System.err.println("Error al insertar Tarea: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // UPDATE
    public int updateTarea(int idTarea, int idProyecto, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("UPDATE Tarea " + 
                                          "SET idProyecto = '" + idProyecto + "', " +
                                              "nombre = '" + nombre +"', " +
                                              "descripcion = '" + descripcion + "', " +
                                              "fechaInicio = '" + java.sql.Date.valueOf(fechaInicio) + "', " +
                                              "fechaFinalizacion = '" + java.sql.Date.valueOf(fechaFinalizacion) + "' " + 
                                          "WHERE idTarea = " + idTarea + ";");
                                          
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al actualizar Tarea: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // DELETE
    public int deleteTarea(int idTarea) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("DELETE Tarea " + 
                                          "WHERE idTarea = " + idTarea + ";");
                                          
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al eliminar Tarea: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // SELECT
    public ArrayList<Tarea> listarTarea() 
    {
        Tarea tarea = null;
        ArrayList<Tarea> tareas = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Tarea;");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                tarea = new Tarea(rs.getInt(1), 
                                        rs.getInt("idProyecto"), 
                                        rs.getString("nombre"), 
                                        rs.getString("descripcion"), 
                                        (sqlFechaInicio != null) ? new java.util.Date(sqlFechaInicio.getTime()) : null,
                                        (sqlFechaFinal != null) ? new java.util.Date(sqlFechaFinal.getTime()) : null);
                
                tareas.add(tarea);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Tarea: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return tareas;
    }
    
    // SELECT POR ID
    public Tarea listarTareaPorID(int idTarea) 
    {
        Tarea tarea = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Tarea WHERE idTarea = " + idTarea + ";");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                tarea = new Tarea(rs.getInt(1), 
                                        rs.getInt("idProyecto"), 
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
            System.err.println("Error al listar Tarea por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return tarea;
    }
}
