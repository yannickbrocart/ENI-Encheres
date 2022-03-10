package encheres.servlets;

import java.io.IOException;
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
import encheres.bll.EnchereManager;
import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Enchere;
import encheres.bo.Utilisateur;

@WebServlet("/EncherirServlet")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVendu articleVendu;
	private Enchere enchere;
	private int noArticleVendu;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categorie> listeCategories = null;
		List<Enchere> listeEncheres = null;
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		CategorieManager categorieManager = new CategorieManager();
		EnchereManager enchereManager = new EnchereManager();
		noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
		try {
			listeCategories = categorieManager.selectAllCategories();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		try {
			articleVendu = articleVenduManager.selectArticleVenduById(noArticleVendu);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		try {
			listeEncheres = enchereManager.selectAllEncheres();
			int montant = 0;
			for (Enchere id : listeEncheres) {
				if (id.getMontantEnchere() > montant) {
					enchere = id;
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("listeCategories", listeCategories);
		request.setAttribute("articleVendu", articleVendu);
		request.setAttribute("enchere", enchere);
		request.getRequestDispatcher("/WEB-INF/jsp/encherir.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		EnchereManager enchereManager = new EnchereManager();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("monProfilUtilisateur");
		enchere = new Enchere(vendeur.getNoUtilisateur(), articleVendu.getNoArticle(),
				articleVendu.getDateFinEncheres(), Integer.parseInt(request.getParameter("enchere")));
		try {
			enchereManager.insertEnchereWithId(enchere);
			request.getRequestDispatcher("/").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
