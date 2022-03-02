package encheres.dal;

import encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import encheres.dal.jdbc.CategorieDAOJdbcImpl;
import encheres.dal.jdbc.EnchereDAOJdbcImpl;
import encheres.dal.jdbc.RetraitDAOJdbcImpl;
import encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}

}
