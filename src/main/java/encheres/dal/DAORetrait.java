package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Retrait;

public interface DAORetrait extends DAO<Retrait> {

	public void insertWithNoArticle(Retrait retrait, int noArticle) throws BusinessException;
}
