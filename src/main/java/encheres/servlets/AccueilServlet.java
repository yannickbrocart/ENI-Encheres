package encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.CategoriesManager;
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
		CategoriesManager categorieManager = new CategoriesManager();
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
