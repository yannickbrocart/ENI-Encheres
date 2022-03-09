package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;

public class ArticleVenduManager {

	DAO<ArticleVendu> articleVenduDAO;

	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	public int insertArticleVenduWithId(ArticleVendu articleVendu) throws BusinessException {
		return articleVenduDAO.insertWithId(articleVendu);
	}

	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		articleVenduDAO.insert(articleVendu);
	}

	public void updateArticleVendu(int identifiant, ArticleVendu articleVendu) throws BusinessException {
		articleVenduDAO.update(articleVendu);
	}

	public void deleteArticleVendu(int identifiant) throws BusinessException {
		articleVenduDAO.delete(identifiant);
	}

	public ArticleVendu selectArticleVenduById(int identifiant) throws BusinessException {
		return articleVenduDAO.selectById(identifiant);
	}

	public List<ArticleVendu> selectAllArticleVendus() throws BusinessException {
		return articleVenduDAO.selectAll();
	}

}
