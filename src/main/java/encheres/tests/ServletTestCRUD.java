package encheres.tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;

@WebServlet("/ServletTestCRUD")
public class ServletTestCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur monProfilUtilisateur = new Utilisateur();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			monProfilUtilisateur = utilisateurManager.selectUtilisateurById(1);
			System.out.println(monProfilUtilisateur.toString());
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
