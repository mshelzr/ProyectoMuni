package com.java.util;

import java.sql.*;

import org.apache.log4j.Logger;

import com.java.seguridad.servlets.LogueoServlet;

public class MySqlConexion {
	private static final Logger log = Logger.getLogger(LogueoServlet.class);
	 
	// tenemos que registrar el driver de conexion
	// Este registro solo se tiene que hacer la  primera vez
	// que se invoque a esta clase (MySqlConexion)
	
	// Definimos un bloque estatico. Un bloque estatico solo
	// se ejecuta la primera vez que se invoca la clase
	static{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static Connection obtenerConexion(){
		
		Connection cn = null;
		
		try {
			
			cn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bdMuni", 
						"root", 
						"mysql");
			log.info("Connection to BD has been success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		
		
		
		return cn;
		
	}
	
}
