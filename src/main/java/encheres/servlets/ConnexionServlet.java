package encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

		request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
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
				monProfilUtilisateur.setMotDePasse(null);
				session.setAttribute("monProfilUtilisateur", monProfilUtilisateur);

				// Création d'un cookie
				if (request.getParameter("remember") != null) {
					Cookie cookie = new Cookie("pseudoUtilisateur", monProfilUtilisateur.getPseudo());
					cookie.setMaxAge(60 * 60 * 24 * 31);
					response.addCookie(cookie);
				}
				// ou Suppression d'un cookie
				else {
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("pseudoUtilisateur")) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
							}
						}
					}
				}

				request.getRequestDispatcher("/").forward(request, response);
			} else
				request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
