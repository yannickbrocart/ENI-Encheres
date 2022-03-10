package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Enchere;

public interface DAOEnchere extends DAO<Enchere> {
	public Enchere selectById(int identifiant_1, int identifiant_2) throws BusinessException;
}
