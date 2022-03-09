package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Retrait;
import encheres.dal.DAORetrait;

public class RetraitDAOJdbcImpl implements DAORetrait {

	private static final String INSERT_RETRAIT = "INSERT INTO retraits(no_article,rue,code_postal,ville) VALUES(?,?,?,?)";
	private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM retraits WHERE no_article=?";

	@Override
	public void insertWithNoArticle(Retrait retrait, int noArticle) throws BusinessException {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT)) {
			pstmt.setInt(1, noArticle);
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Retrait retrait) throws BusinessException {
	}

	@Override
	public Retrait selectById(int identifiant) throws BusinessException {
		Retrait retrait = null;
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID)) {
			pstmt.setInt(1, identifiant);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public void delete(int identifiant) throws BusinessException {
	}

	@Override
	public void insert(Retrait retrait) throws BusinessException {
	}

	@Override
	public int insertWithId(Retrait retrait) throws BusinessException {
		return 0;
	}

}
