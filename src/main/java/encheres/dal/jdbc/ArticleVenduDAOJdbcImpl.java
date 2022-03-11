package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;
import encheres.bo.Retrait;
import encheres.bo.Utilisateur;
import encheres.dal.DAO;

public class ArticleVenduDAOJdbcImpl implements DAO<ArticleVendu> {

	private static final String INSERT_ARTICLE = "INSERT INTO articles_vendus(nom_article,description_article,date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,vente_active,no_utilisateur,no_categorie, article_supprime) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM articles_vendus LEFT JOIN retraits ON articles_vendus.no_article = retraits.no_article"
			+ " INNER JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur";
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM articles_vendus INNER JOIN retraits ON articles_vendus.no_article = retraits.no_article"
			+ " INNER JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE articles_vendus.no_article=?";
	private static final String UPDATE_ARTICLE = "UPDATE articles_vendus SET nom_article=?,description_article=?,date_debut_encheres=?,date_fin_encheres=?,"
			+ "prix_initial=?,prix_vente=?,vente_active=?,no_utilisateur=?,no_categorie=?, article_supprime=? WHERE no_article=?";
	private static final String DELETE_ARTICLE = "DELETE FROM articles_vendus WHERE no_article=?";

	@Override
	public int insertWithId(ArticleVendu articleVendu) throws BusinessException {
		int noArticleVendu = 0;
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setBoolean(7, articleVendu.isVenteActive());
			pstmt.setInt(8, articleVendu.getVendeur().getNoUtilisateur());
			pstmt.setInt(9, articleVendu.getCategorieArticle());
			pstmt.setBoolean(10, false);
			pstmt.executeUpdate();
			ResultSet key = pstmt.getGeneratedKeys();
			key.next();
			noArticleVendu = key.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noArticleVendu;
	}

	@Override
	public void insert(ArticleVendu s) throws BusinessException {
	}

	@Override
	public void update(int identifiant, ArticleVendu articleVendu) throws BusinessException {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE)) {
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setBoolean(7, articleVendu.isVenteActive());
			pstmt.setInt(8, articleVendu.getVendeur().getNoUtilisateur());
			pstmt.setInt(9, articleVendu.getCategorieArticle());
			pstmt.setBoolean(10, articleVendu.isArticleSupprime());
			pstmt.setInt(11, identifiant);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleVendu selectById(int identifiant) throws BusinessException {
		Retrait lieuRetrait = new Retrait();
		ArticleVendu articleVendu = null;
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);) {
			pstmt.setInt(1, identifiant);
			ResultSet rs = pstmt.executeQuery();
			// MAPPING
			if (rs.next()) {
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description_article");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				Boolean venteActive = rs.getBoolean("vente_active");
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				int categorieArticle = rs.getInt("no_categorie");
				lieuRetrait.setRue(rs.getString("rue"));
				lieuRetrait.setCodePostal(rs.getString("code_postal"));
				lieuRetrait.setVille(rs.getString("ville"));
				Boolean articleSupprime = rs.getBoolean("article_supprime");
				articleVendu = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres,
						miseAPrix, prixVente, venteActive, utilisateur, categorieArticle, lieuRetrait, articleSupprime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVendu;

	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();

		Retrait lieuRetrait = new Retrait();
		try (Connection cnx = PoolConnection.getConnection();
				ResultSet rs = cnx.createStatement().executeQuery(SELECT_ALL_ARTICLES);) {
			// MAPPING
			ArticleVendu articleVendu;
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description_article");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				Boolean venteActive = rs.getBoolean("vente_active");
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				int categorieArticle = rs.getInt("no_categorie");
				lieuRetrait.setRue(rs.getString("rue"));
				lieuRetrait.setCodePostal(rs.getString("code_postal"));
				lieuRetrait.setVille(rs.getString("ville"));
				Boolean articleSupprime = rs.getBoolean("article_supprime");
				articleVendu = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres,
						miseAPrix, prixVente, venteActive, utilisateur, categorieArticle, lieuRetrait, articleSupprime);
				listeArticleVendu.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticleVendu;
	}

	@Override
	public void delete(int identifiant) throws BusinessException {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE)) {
			pstmt.setInt(1, identifiant);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
