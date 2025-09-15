package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class csConnection {
    public String user;
    public String password;
    public String url;
    public String driver;
    public Connection cn;
    
    public csConnection() {
        this.user = "sa";
        this.password = "root";
        this.url = "jdbc:sqlserver://DESKTOP-76887SE\\DESKTOP-76887SE:1433;databaseName=db_ganttProject";
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        this.cn = null;
    }
    
    public Connection connect() {
        try 
        {
            Class.forName(this.driver);
            cn = DriverManager.getConnection(url, user, password);
            return cn;
        }
        catch(Exception ex)
        {
               
            return null;
        }
    }
    
    public void disconnect() throws SQLException{
        cn.close();
    }
}
