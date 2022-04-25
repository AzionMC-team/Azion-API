package fr.azion.mathis_bruel.api.DataBase;

public class DbCredentials {
	
	private String host;
	
	private String user;
	
	private String pass;
	
	private String dbname;
	
	private int port;

	public DbCredentials(String host, String user, String pass, String dbname, int port) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.dbname = dbname;
		this.port = port;
	}
	
	public String toURI() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(dbname);
		return sb.toString();
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
}
