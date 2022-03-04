package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Utilisateur;

public interface DAOUtilisateur extends DAO<Utilisateur> {

	public Utilisateur connexion(String login, String motDePasse) throws BusinessException;
}
