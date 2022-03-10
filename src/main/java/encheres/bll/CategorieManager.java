package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Categorie;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;

public class CategorieManager {

	DAO<Categorie> categorieDAO;

	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}

	public List<Categorie> selectAllCategories() throws BusinessException {
		return categorieDAO.selectAll();
	}

}
