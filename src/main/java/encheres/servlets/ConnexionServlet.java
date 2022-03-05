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

@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur monProfilUtilisateur = null;
		String encryptedPassword = null;

		// Encryption du password
		try {
			encryptedPassword = LoginAndPasswordSecurity.passwordEncryption(request.getParameter("password"));
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}

		// Test existance combinaison login-password et si OK récupération utilisateur
		try {
			monProfilUtilisateur = utilisateurManager.connexion(request.getParameter("login"), encryptedPassword);
			// Création d'une session
			if (monProfilUtilisateur != null) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(300);
				monProfilUtilisateur.setMotDePasse(null);
				session.setAttribute("monProfilUtilisateur", monProfilUtilisateur);
				request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
