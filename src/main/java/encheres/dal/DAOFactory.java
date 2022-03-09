package encheres.dal;

import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Enchere;
import encheres.bo.Retrait;
import encheres.bo.Utilisateur;
import encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import encheres.dal.jdbc.CategorieDAOJdbcImpl;
import encheres.dal.jdbc.EnchereDAOJdbcImpl;
import encheres.dal.jdbc.RetraitDAOJdbcImpl;
import encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static DAOUtilisateur getUtilisateurDAO2() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static DAO<ArticleVendu> getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}

	public static DAO<Enchere> getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static DAO<Categorie> getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static DAO<Retrait> getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}

	public static DAORetrait getRetraitDAO2() {
		return new RetraitDAOJdbcImpl();
	}

}
