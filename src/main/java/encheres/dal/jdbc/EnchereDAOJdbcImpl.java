package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;
import encheres.dal.DAO;
import encheres.dal.DAOEnchere;

public class EnchereDAOJdbcImpl implements DAO<Enchere>, DAOEnchere {

	private static final String INSERT_ENCHERE = "INSERT INTO encheres(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?)";
	private static final String SELECT_ENCHERE_BY_ID = "SELECT * FROM encheres WHERE no_utilisateur=? AND no_article=?";
	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM encheres";

	@Override
	public int insertWithId(Enchere enchere) throws BusinessException {
		int noEnchere = 0;
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setInt(2, enchere.getNoArticle());
			pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			ResultSet key = pstmt.getGeneratedKeys();
			key.next();
			noEnchere = key.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noEnchere;
	}

	@Override
	public void insert(Enchere enchere) throws BusinessException {
	}

	@Override
	public void update(int identifiant, Enchere enchere) throws BusinessException {
	}

	@Override
	public Enchere selectById(int identifiant) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectById(int identifiant_1, int identifiant_2) throws BusinessException {
		Enchere enchere = null;
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_ID);) {
			pstmt.setInt(1, identifiant_1);
			pstmt.setInt(2, identifiant_2);
			ResultSet rs = pstmt.executeQuery();
			// MAPPING
			if (rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noArticle = rs.getInt("no_article");
				LocalDate dateFinEncheres = rs.getDate("date_enchere").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				enchere = new Enchere(noUtilisateur, noArticle, dateFinEncheres, montantEnchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<>();
		try (Connection cnx = PoolConnection.getConnection();
				ResultSet rs = cnx.createStatement().executeQuery(SELECT_ALL_ENCHERES);) {
			// MAPPING
			while (rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noArticle = rs.getInt("no_article");
				LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				Enchere enchere = new Enchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
				listeEncheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeEncheres;
	}

	@Override
	public void delete(int identifiant) throws BusinessException {
	}

}
