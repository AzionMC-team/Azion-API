package fr.azion.mathis_bruel.api.DataBase;

import java.sql.SQLException;

public class dbmanageur {
	
	private connection connectionmysql;
	
	public dbmanageur() {
		this.connectionmysql = new connection(new DbCredentials("192.168.11.201", "dev", "cHU9J8pPlzf2kVZS", "Azion", 3306));
	}
	
	public connection getConnection() {
		return connectionmysql;
	}

	public void close() {
		try {
			this.connectionmysql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
