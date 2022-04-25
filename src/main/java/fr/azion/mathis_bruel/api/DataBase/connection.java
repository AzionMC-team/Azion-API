package fr.azion.mathis_bruel.api.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class connection {
	
	private DbCredentials dbCredentials;
	private Connection connection;
	
	public connection(DbCredentials dbCredentials) {
		this.dbCredentials = dbCredentials;
		this.connect();
		
		
	}
	
	private void connect() {
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				this.connection = DriverManager.getConnection(this.dbCredentials.toURI(), this.dbCredentials.getUser(), this.dbCredentials.getPass());
				Logger.getLogger("minecraft").info("successfully connected to DB.");
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void close() throws SQLException {
		
		if(this.connection != null) {
			if(!this.connection.isClosed()){

					this.connection.close();
				
			}
		}
		
	}
	
	public Connection getConnection() throws SQLException {
		
		if(this.connection != null) {
			if(!this.connection.isClosed()) {
				return (Connection) this.connection;
			}
		}
		connect();
		return (Connection) this.connection;
		
	}

	

	


	
	
}
