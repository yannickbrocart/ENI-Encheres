package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Categorie;
import encheres.dal.DAO;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {

	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";

	@Override
	public int insertWithId(Categorie s) throws BusinessException {
		return 0;
	}

	@Override
	public void insert(Categorie s) throws BusinessException {
	}

	@Override
	public void update(Categorie s) throws BusinessException {
	}

	@Override
	public Categorie selectById(int identifiant) throws BusinessException {
		return null;
	}

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> listeCategories = new ArrayList<>();
		try (Connection cnx = PoolConnection.getConnection();
				ResultSet rs = cnx.createStatement().executeQuery(SELECT_ALL_CATEGORIES);) {
			// MAPPING
			Categorie categorie = null;
			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listeCategories.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}

	@Override
	public void delete(int identifiant) throws BusinessException {
	}

}
