package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws BusinessException;

	public void update(int identifiant, Utilisateur utilisateur) throws BusinessException;

	public void desactivate(int identifiant) throws BusinessException;

	public void delete(int identifiant) throws BusinessException;

	public Utilisateur select(int identifiant) throws BusinessException;

}
