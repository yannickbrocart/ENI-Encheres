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

@WebServlet("/EnchereNonCommenceeServlet")
public class EnchereNonCommenceeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVendu articleVendu;
	private int noArticleVendu;
	public Retrait retrait;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categorie> listeCategories = null;
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		CategorieManager categorieManager = new CategorieManager();
		noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
		try {
			articleVendu = articleVenduManager.selectArticleVenduById(noArticleVendu);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if (articleVendu.getDateDebutEncheres().compareTo(LocalDate.now()) > 0) {
			try {
				listeCategories = categorieManager.selectAllCategories();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeCategories", listeCategories);
			request.setAttribute("articleVendu", articleVendu);
			request.getRequestDispatcher("/WEB-INF/jsp/enchereNonCommencee.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/EncherirServlet").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RetraitManager retraitManager = new RetraitManager();
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		if (request.getParameter("submit").equals("annuler")) {
			System.out.println();
			try {
				retraitManager.deleteRetrait(articleVendu.getNoArticle());
				articleVenduManager.deleteArticleVendu(articleVendu.getNoArticle());
				request.getRequestDispatcher("/").forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("submit").equals("enregistrer")) {
			HttpSession session = request.getSession();
			Utilisateur vendeur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
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
				articleVenduManager.updateArticleVendu(noArticleVendu, articleVendu);
				retraitManager.updateRetrait(noArticleVendu, retrait);
				request.getRequestDispatcher("/").forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

}
