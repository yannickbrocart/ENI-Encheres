package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;

public class EnchereManager {
	DAO<Enchere> enchereDAO;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void insertEnchere(Enchere enchere) throws BusinessException {
		enchereDAO.insert(enchere);
	}

	public int insertEnchereWithId(Enchere enchere) throws BusinessException {
		return enchereDAO.insertWithId(enchere);
	}

	public void updateEnchere(int identifiant, Enchere enchere) throws BusinessException {
		enchereDAO.update(identifiant, enchere);
	}

	public void deleteEnchere(int identifiant) throws BusinessException {
		enchereDAO.delete(identifiant);
	}

	public Enchere selectEnchereById(int identifiant) throws BusinessException {
		return enchereDAO.selectById(identifiant);
	}

	public List<Enchere> selectAllEncheres() throws BusinessException {
		return enchereDAO.selectAll();
	}
}
