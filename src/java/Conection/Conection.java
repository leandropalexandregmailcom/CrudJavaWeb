/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conection{

    private static Connection connection = null;
  
    public static Connection getConnection() throws ClassNotFoundException
    {
        if(connection == null)
        {
            try{
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/padrao", "postgres", "postgres");
            }catch(SQLException e)
            {
                System.out.println("Erro: "+e);
            }
        }
       
        return connection;
    }
}
