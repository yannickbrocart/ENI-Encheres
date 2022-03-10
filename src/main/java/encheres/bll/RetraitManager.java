package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Retrait;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;
import encheres.dal.DAORetrait;

public class RetraitManager {
	DAO<Retrait> retraitDAO;
	DAORetrait retraitDAO2;

	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
		retraitDAO2 = DAOFactory.getRetraitDAO2();
	}

	public void insertWithNoArticle(Retrait retrait, int noArticle) throws BusinessException {
		retraitDAO2.insertWithNoArticle(retrait, noArticle);
	}

	public void insertRetrait(Retrait retrait) throws BusinessException {
		retraitDAO.insert(retrait);
	}

	public void insertRetraitById(Retrait retrait) throws BusinessException {
		retraitDAO.insertWithId(retrait);
	}

	public void updateRetrait(int identifiant, Retrait retrait) throws BusinessException {
		retraitDAO.update(identifiant, retrait);
	}

	public void deleteRetrait(int identifiant) throws BusinessException {
		retraitDAO.delete(identifiant);
	}

	public Retrait selectRetraitById(int identifiant) throws BusinessException {
		return retraitDAO.selectById(identifiant);
	}

	public List<Retrait> selectAllRetraits() throws BusinessException {
		return retraitDAO.selectAll();
	}
}
