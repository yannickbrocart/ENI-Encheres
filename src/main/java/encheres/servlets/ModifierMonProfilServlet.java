package encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;
import encheres.security.LoginAndPasswordSecurity;

@WebServlet("/ModifierMonProfilServlet")
public class ModifierMonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur monProfilUtilisateur;
	private int noUtilisateur;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
		noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			utilisateur = utilisateurManager.selectUtilisateurById(noUtilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/modifierMonProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
		noUtilisateur = utilisateur.getNoUtilisateur();

		try {
			monProfilUtilisateur = utilisateurManager.selectUtilisateurById(noUtilisateur);
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}

		if (request.getParameter("submit").equals("supprimer")) {
			try {
				utilisateurManager.deleteUtilisateur(noUtilisateur);
				request.getRequestDispatcher("/DeconnexionServlet").forward(request, response);
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
					monProfilUtilisateur.setNoUtilisateur(noUtilisateur);
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
						request.getRequestDispatcher("/").forward(request, response);
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
