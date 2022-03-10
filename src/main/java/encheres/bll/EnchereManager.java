package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;
import encheres.dal.DAO;
import encheres.dal.DAOEnchere;
import encheres.dal.DAOFactory;

public class EnchereManager {
	DAO<Enchere> enchereDAO;
	DAOEnchere enchereDAO2;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
		enchereDAO2 = DAOFactory.getEnchereDAO2();
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

	public Enchere selectEnchereById(int identifiant_1, int identifiant_2) throws BusinessException {
		return enchereDAO2.selectById(identifiant_1, identifiant_2);
	}

	public List<Enchere> selectAllEncheres() throws BusinessException {
		return enchereDAO.selectAll();
	}
}
