package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class csReporte {
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    public csReporte() {
        this.con = null;
        this.stm = null;
    }
    
    public ArrayList<Tarea> listarTareasPorProyecto(int idProyecto) 
    {
        Tarea tarea = null;
        ArrayList<Tarea> tareas = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Tarea WHERE idProyecto = " + idProyecto + ";");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                tarea = new Tarea (rs.getInt(1), 
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
            System.err.println("Error al listar Tareas: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return tareas;
    }
    
    public ArrayList<Actividad> listarActividadesPorTarea(int idTarea) 
    {
        Actividad actividad = null;
        ArrayList<Actividad> actividades = new ArrayList<>();
        
        csConnection cl = new csConnection();
        con = cl.connect();
        rs = null;
        
        try 
        {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Actividad WHERE idTarea = " + idTarea + ";");
            
            while(rs.next())
            {
                java.sql.Date sqlFechaInicio = rs.getDate("fechaInicio");
                java.sql.Date sqlFechaFinal = rs.getDate("fechaFinalizacion");
                
                /*
                LocalDate fechaInicio = (sqlFechaInicio != null) ? sqlFechaInicio.toLocalDate() : null;
                LocalDate fechaFinal = (sqlFechaFinal != null) ? sqlFechaFinal.toLocalDate() : null;
                */
                
                actividad = new Actividad (rs.getInt(1), 
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
            System.err.println("Error al listar Actividades: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return actividades;
    }
}
