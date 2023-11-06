package com.ejemploSQL.clase13_Ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App 
{
    public static void main( String[] args )
    {
    	Connection conexion = null;
    	Statement consulta = null;
    	
    	try {
			// 1. Realizar conexión con la DB creada en el punto 2 del ejercicio de la clase 12.	
    		conexion = DriverManager.getConnection(ConectorSQL.DB_URL, ConectorSQL.USER, ConectorSQL.PASS);
    		
    		// 2. Ejecutar consultas
    		consulta = conexion.createStatement();
    		// Insertar un empleado nuevo en la nómina de trabajo
    		String query = "INSERT Empleado (DNI, Nombre, Apellido, Nacionalidad, idDepartamento)\r\n"
    						+ "VALUES (30888976, \"Carolina\", \"Gonzalez\", \"Argentina\", 1);";
    		
    		consulta.executeUpdate(query);
    		
    		    		
    		// Modificar la nacionalidad de algún empleado
    		query = "UPDATE Empleado\r\n"
    				+ "SET Nacionalidad = \"Colombia\"\r\n"
    				+ "WHERE DNI = 30888976;";
    		
    		consulta.executeUpdate(query);
    		
    		// Eliminar un departamento
    		query = "DELETE FROM Departamento\r\n"
    				+ "WHERE IdDepartamento = 6";
    		
    		consulta.executeUpdate(query);
    		
    		// Conocer los empleados que trabajan en el departamento de “logística”
    		query = "SELECT * FROM Empleado\r\n"
    				+ "WHERE IdDepartamento = 1;";
    		
    		ResultSet resultado = consulta.executeQuery(query);
    		
    		System.out.println("Los empleados que trabajan en el departamento de Lgística son: ");
    		
    		while (resultado.next()) {
    			System.out.println(resultado.getString(2) + " " + resultado.getString(3) + " - DNI: " + resultado.getString(1));
    		}
			
    		// Mostrar todos los departamentos ordenados alfabéticamente
    		query = "SELECT * FROM Departamento\r\n" 
    				+ "ORDER BY Nombre;";
    		resultado = consulta.executeQuery(query);
    		
    		System.out.println("Los siguientes son los departamentos actuales: ");
    		
    		while (resultado.next()) {
    			System.out.println(resultado.getString(2));
    		}
			
    		
    	}catch (SQLException e) {
		
    		e.printStackTrace();
		}
    	finally {
    		try {
				conexion.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
    	}
    }
}
