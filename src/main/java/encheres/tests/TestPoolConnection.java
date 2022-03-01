package encheres.tests;

import java.sql.Connection;
import java.sql.SQLException;

import encheres.dal.jdbc.PoolConnection;

public class TestPoolConnection {

	public static void testConnexion() {
		try {
			Connection cnx = PoolConnection.getConnection();
			System.out.println("La connexion est établie !");
			cnx.close();
			System.out.println("La connexion est fermée !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
