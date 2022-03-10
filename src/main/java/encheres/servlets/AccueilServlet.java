package encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.CategorieManager;
import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;

@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categorie> listeCategories = null;
		List<ArticleVendu> listeArticlesVendus = null;
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		CategorieManager categorieManager = new CategorieManager();
		try {
			listeArticlesVendus = articleVenduManager.selectAllArticleVendus();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		try {
			listeCategories = categorieManager.selectAllCategories();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// Test cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("pseudoUtilisateur")) {
					// Création d'une session ?
				}
			}
		}
		request.setAttribute("now", LocalDate.now());
		request.setAttribute("listeArticlesVendus", listeArticlesVendus);
		request.setAttribute("listeCategories", listeCategories);
		request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit").equals("rechercher")) {
			request.setAttribute("categorie", request.getParameter("categorie"));
		}
		doGet(request, response);
	}

}
