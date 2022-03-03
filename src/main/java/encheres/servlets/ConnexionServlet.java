package encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
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
		try {
			LoginAndPasswordSecurity.validateLoginAndPassword(request.getParameter("login"),
					request.getParameter("password"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		String encryptedPassword = null;
		try {
			encryptedPassword = LoginAndPasswordSecurity.passwordEncryption(request.getParameter("password"));
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}

		utilisateurManager.insertUtilisateur(utilisateur);

		request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp").forward(request, response);
	}

}
