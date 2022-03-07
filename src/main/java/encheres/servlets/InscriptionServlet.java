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

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Utilisateur monProfilUtilisateur = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int noUtilisateur = 0;

		// Si 2 passwords identiques
		if (request.getParameter("motdepasse").equals(request.getParameter("motdepasseconfirme"))) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			String encryptedPassword = null;
			try {
				encryptedPassword = LoginAndPasswordSecurity.passwordEncryption(request.getParameter("motdepasse"));
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			monProfilUtilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"),
					request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
					request.getParameter("rue"), request.getParameter("codepostal"), request.getParameter("ville"),
					encryptedPassword);
			try {
				noUtilisateur = utilisateurManager.insertUtilisateur(monProfilUtilisateur);
				monProfilUtilisateur.setNoUtilisateur(noUtilisateur);
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(300);
				monProfilUtilisateur.setMotDePasse(null);
				session.setAttribute("monProfilUtilisateur", monProfilUtilisateur);
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else {
			// throw new ServletException();
			System.out.println("Confirmation nouveau mot de passe erronnée");
			request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		}
	}

}
