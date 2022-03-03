package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Utilisateur;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;

public class UtilisateurManager {

	DAO<Utilisateur> utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.insert(utilisateur);
	}

	public void updateUtilisateur(int identifiant, Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.update(utilisateur);
	}

	public void deleteUtilisateur(int identifiant) throws BusinessException {
		utilisateurDAO.delete(identifiant);
	}

	public Utilisateur selectUtilisateurById(int identifiant) throws BusinessException {
		return utilisateurDAO.selectById(identifiant);
	}

	public List<Utilisateur> selectAllUtilisateurs(int identifiant) throws BusinessException {
		return utilisateurDAO.selectAll();
	}

}
