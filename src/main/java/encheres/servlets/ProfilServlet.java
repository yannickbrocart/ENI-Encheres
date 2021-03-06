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

@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur();
		HttpSession session = request.getSession();
		Utilisateur monProfilUtilisateur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
		int monNoUtilisateur = monProfilUtilisateur.getNoUtilisateur();

		// lien vers profil étranger
		if (request.getParameter("modifier") == null
				&& !(Integer.parseInt(request.getParameter("noVendeur")) == monNoUtilisateur)) {
			try {
				utilisateur = utilisateurManager
						.selectUtilisateurById(Integer.parseInt(request.getParameter("noVendeur")));
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
			}
		}

		else {
			// lien vers mon profil
			request.setAttribute("modifier", "true");
			try {
				utilisateur = utilisateurManager.selectUtilisateurById(monNoUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
