package encheres.bo;

import java.time.LocalDate;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private boolean venteActive;
	private Utilisateur vendeur;
	private int categorieArticle;
	private Retrait lieuRetrait;
	private boolean articleSupprime;
	private byte[] photo;

	public ArticleVendu() {
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, boolean venteActive, Utilisateur vendeur, int categorieArticle,
			Retrait lieuRetrait, boolean articleSupprime) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.venteActive = venteActive;
		this.vendeur = vendeur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
		this.articleSupprime = articleSupprime;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean venteActive, Utilisateur vendeur,
			int categorieArticle, Retrait lieuRetrait, boolean articleSupprime, byte[] photo) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.venteActive = venteActive;
		this.vendeur = vendeur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
		this.articleSupprime = articleSupprime;
		this.photo = photo;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean venteActive, Utilisateur vendeur,
			int categorieArticle, Retrait lieuRetrait, boolean articleSupprime) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.venteActive = venteActive;
		this.vendeur = vendeur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
		this.articleSupprime = articleSupprime;
		this.photo = null;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isVenteActive() {
		return venteActive;
	}

	public void setVenteActive(boolean venteActive) {
		this.venteActive = venteActive;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public int getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(int categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setNoLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public boolean isArticleSupprime() {
		return articleSupprime;
	}

	public void setArticleSupprime(boolean articleSupprime) {
		this.articleSupprime = articleSupprime;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle = " + noArticle + ", nomArticle = " + nomArticle + ", description = "
				+ description + ", dateDebutEncheres = " + dateDebutEncheres + ", dateFinEncheres = " + dateFinEncheres
				+ ", miseAPrix = " + miseAPrix + ", prixVente = " + prixVente + ", venteActive = " + venteActive
				+ ", Vendeur = " + vendeur + ", categorieArticle = " + categorieArticle + ", lieuRetrait = "
				+ lieuRetrait + ", articleSupprime = " + articleSupprime + "]";
	}

}
