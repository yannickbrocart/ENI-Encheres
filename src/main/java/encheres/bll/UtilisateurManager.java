package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Utilisateur;
import encheres.dal.DAO;
import encheres.dal.DAOFactory;
import encheres.dal.DAOUtilisateur;

public class UtilisateurManager {

	DAO<Utilisateur> utilisateurDAO;
	DAOUtilisateur utilisateurDAO2;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurDAO2 = DAOFactory.getUtilisateurDAO2();
	}

	public int insertUtilisateurWithId(Utilisateur utilisateur) throws BusinessException {
		return utilisateurDAO.insertWithId(utilisateur);
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

	public List<Utilisateur> selectAllUtilisateurs() throws BusinessException {
		return utilisateurDAO.selectAll();
	}

	public Utilisateur connexion(String login, String motDePasse) throws BusinessException {
		return utilisateurDAO2.connexion(login, motDePasse);
	}

}
