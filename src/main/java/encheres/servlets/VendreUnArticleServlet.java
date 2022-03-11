package encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encheres.BusinessException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.CategorieManager;
import encheres.bll.RetraitManager;
import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Retrait;
import encheres.bo.Utilisateur;

@WebServlet("/VendreUnArticleServlet")
public class VendreUnArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArticleVendu articleVendu = null;
	public Retrait retrait = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categorie> listeCategories = null;
		CategorieManager categorieManager = new CategorieManager();
		try {
			listeCategories = categorieManager.selectAllCategories();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("now", LocalDate.now());
		request.setAttribute("listeCategories", listeCategories);
		request.getRequestDispatcher("/WEB-INF/jsp/vendreUnArticle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		RetraitManager retraitManager = new RetraitManager();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
		int noArticleVendu = 0;
		boolean venteActive = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		retrait = new Retrait(request.getParameter("rue"), request.getParameter("codePostal"),
				request.getParameter("ville"));
		articleVendu = new ArticleVendu(request.getParameter("article"), request.getParameter("description"),
				LocalDate.parse(request.getParameter("dateDebutEncheres"), dtf),
				LocalDate.parse(request.getParameter("dateFinEncheres"), dtf),
				Integer.parseInt(request.getParameter("miseAPrix")), 0, venteActive, vendeur,
				Integer.parseInt(request.getParameter("categorie")), retrait, false);
		try {
			noArticleVendu = articleVenduManager.insertArticleVenduWithId(articleVendu);
			retraitManager.insertWithNoArticle(retrait, noArticleVendu);
			request.getRequestDispatcher("/").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

}
