package encheres.bll;

import encheres.BusinessException;
import encheres.bo.Utilisateur;
import encheres.dal.DAOFactory;
import encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.insert(utilisateur);
	}

	public void updateUtilisateur(int identifiant, Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.update(identifiant, utilisateur);
	}

	public void desactivateUtilisateur(int identifiant) throws BusinessException {
		utilisateurDAO.desactivate(identifiant);
	}

	public void deleteUtilisateur(int identifiant) throws BusinessException {
		utilisateurDAO.delete(identifiant);
	}

	public Utilisateur selectUtilisateur(int identifiant) throws BusinessException {
		return utilisateurDAO.select(identifiant);
	}

}
