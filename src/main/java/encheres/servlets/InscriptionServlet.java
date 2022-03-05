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

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Si 2 nouveaux password identiques
		if (request.getParameter("motdepasse").equals(request.getParameter("motdepasseconfirme"))) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			String encryptedPassword = null;
			try {
				encryptedPassword = LoginAndPasswordSecurity.passwordEncryption(request.getParameter("motdepasse"));
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			Utilisateur monProfilUtilisateur = new Utilisateur(request.getParameter("pseudo"),
					request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"),
					request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("codepostal"),
					request.getParameter("ville"), encryptedPassword);
			try {
				utilisateurManager.insertUtilisateur(monProfilUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else {
			// throw new ServletException();
			System.out.println("Confirmation nouveau mot de passe erronnée");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp").forward(request, response);
	}

}
