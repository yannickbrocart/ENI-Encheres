package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolConnection {

	private static DataSource dataSource;

	// Initialisation statique
	static {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données TROCENCHERES_DB !");
		}
	}

	// Connexion
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}