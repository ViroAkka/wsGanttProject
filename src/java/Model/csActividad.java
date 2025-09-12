package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class csActividad {
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    public csActividad() {
        this.con = null;
        this.stm = null;
    }
    
    // INSERT
    public int insertActividad(int idTarea, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("INSERT INTO Actividad " + 
                                          "(idTarea, nombre, descripcion, fechaInicio, fechaFinalizacion) " +
                                          "VALUES ('" + idTarea +"', '" +
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
            System.err.println("Error al insertar Actividad: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // UPDATE
    public int updateActividad(int idActividad, int idTarea, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("UPDATE Actividad " + 
                                          "SET idTarea = '" + idTarea + "', " +
                                              "nombre = '" + nombre +"', " +
                                              "descripcion = '" + descripcion + "', " +
                                              "fechaInicio = '" + java.sql.Date.valueOf(fechaInicio) + "', " +
                                              "fechaFinalizacion = '" + java.sql.Date.valueOf(fechaFinalizacion) + "' " + 
                                          "WHERE idActividad = " + idActividad + ";");
                                          
            
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al actualizar Actividad: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // DELETE
    public int deleteActividad(int idActividad) {
        int respuesta = 0;
        
        csConnection cl = new csConnection();
        
        con = cl.connect();
        try
        {
            stm = con.createStatement();
            respuesta = stm.executeUpdate("DELETE Actividad " + 
                                          "WHERE idActividad = " + idActividad + ";");
                                          
            cl.disconnect();
            
            con.close();
            stm.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al eliminar Actividad: " + ex.getMessage());
            ex.printStackTrace();
        }
                
        return respuesta;
    }
    
    // SELECT
    public ArrayList<Actividad> listarActividad() 
    {
        Actividad actividad = null;
        ArrayList<Actividad> actividades = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Actividad;");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                actividad = new Actividad(rs.getInt(1), 
                                        rs.getInt("idTarea"), 
                                        rs.getString("nombre"), 
                                        rs.getString("descripcion"), 
                                        (sqlFechaInicio != null) ? new java.util.Date(sqlFechaInicio.getTime()) : null,
                                        (sqlFechaFinal != null) ? new java.util.Date(sqlFechaFinal.getTime()) : null);
                
                actividades.add(actividad);
            }
            
            cl.disconnect();
            stm.close();
            con.close();
        }
        catch(SQLException ex) 
        {
            System.err.println("Error al listar Actividad: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return actividades;
    }
    
    // SELECT POR ID
    public Actividad listarActividadPorID(int idActividad) 
    {
        Actividad actividad = null;
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Actividad WHERE idActividad = " + idActividad + ";");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");

                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                actividad = new Actividad(rs.getInt(1), 
                                        rs.getInt("idTarea"), 
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
            System.err.println("Error al listar Actividad por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return actividad;
    }
}
    