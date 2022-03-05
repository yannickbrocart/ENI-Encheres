package encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;
import encheres.security.LoginAndPasswordSecurity;

@WebServlet("/ModifierMonProfilServlet")
public class ModifierMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Utilisateur monProfilUtilisateur;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurManager.selectUtilisateurById(15);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/ModifierMonProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		try {
			monProfilUtilisateur = utilisateurManager.selectUtilisateurById(15);
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}

		if (request.getParameter("submit").equals("supprimer")) {
			try {
				utilisateurManager.deleteUtilisateur(14);// monProfilUtilisateur.getNoUtilisateur());
				System.out.println("Utilisateur supprimé");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("submit").equals("enregistrer")) {

			String encryptedPassword = null, newEncryptedPassword = null;
			try {
				encryptedPassword = LoginAndPasswordSecurity
						.passwordEncryption(request.getParameter("motdepasseactuel"));
			} catch (BusinessException e2) {
				e2.printStackTrace();
			}

			// Si ancien password exact
			if (encryptedPassword.equals(monProfilUtilisateur.getMotDePasse())) {

				// Si 2 nouveaux password identiques
				if (request.getParameter("nouveaumotdepasse")
						.equals(request.getParameter("nouveaumotdepasseconfirme"))) {

					// Update utilisateur
					try {
						newEncryptedPassword = LoginAndPasswordSecurity
								.passwordEncryption(request.getParameter("nouveaumotdepasse"));
					} catch (BusinessException e1) {
						e1.printStackTrace();
					}
					monProfilUtilisateur.setNoUtilisateur(15);
					monProfilUtilisateur.setPseudo(request.getParameter("pseudo"));
					monProfilUtilisateur.setNom(request.getParameter("nom"));
					monProfilUtilisateur.setPrenom(request.getParameter("prenom"));
					monProfilUtilisateur.setEmail(request.getParameter("email"));
					monProfilUtilisateur.setTelephone(request.getParameter("telephone"));
					monProfilUtilisateur.setRue(request.getParameter("rue"));
					monProfilUtilisateur.setCodePostal(request.getParameter("codepostal"));
					monProfilUtilisateur.setVille(request.getParameter("ville"));
					monProfilUtilisateur.setMotDePasse(newEncryptedPassword);
					try {
						utilisateurManager.updateUtilisateur(monProfilUtilisateur.getNoUtilisateur(),
								monProfilUtilisateur);
						System.out.println("Mise à jour réussie");
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				} else
					// throw new ServletException();
					System.out.println("Confirmation nouveau mot de passe erronnée");
			} else
				// throw new ServletException();
				System.out.println("Mauvais ancien mot de passe");
		}
	}
}
