package encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import encheres.bo.Utilisateur;
import encheres.dal.DAO;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {

	private static final String INSERT_UTILISATEUR = "INSERT INTO utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,"
			+ "ville,mot_de_passe,credit,administrateur,utilisateur_desactive,utilisateur_supprime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_UTILISATEUR = "UPDATE utilisateurs SET pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,"
			+ "ville=?,mot_de_passe=?, credit=?,administrateur=?,utilisateur_desactive=?,utilisateur_supprime=? WHERE no_utilisateur=?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_UTILISATEUR_BY = "SELECT * FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_ALL_UTILISATEURS = "SELECT * FROM utilisateurs";
	public Utilisateur monProfilUtilisateur;

	@Override
	public void insert(Utilisateur utilisateur) {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.setBoolean(12, utilisateur.isUtilisateurDesactive());
			pstmt.setBoolean(13, utilisateur.isUtilisateurSupprime());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			monProfilUtilisateur.setNoUtilisateur(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Utilisateur utilisateur) {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR)) {
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.setBoolean(12, utilisateur.isUtilisateurDesactive());
			pstmt.setBoolean(13, utilisateur.isUtilisateurSupprime());
			pstmt.setInt(14, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur selectById(int identifiant) {
		Utilisateur utilisateur = null;
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY)) {
			pstmt.setInt(1, identifiant);
			ResultSet rs = pstmt.executeQuery();
			// MAPPING
			int noUtilisateur = identifiant;
			if (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");
				boolean utilisateurDesactive = rs.getBoolean("utilisateur_desactive");
				boolean utilisateurSupprime = rs.getBoolean("utilisateur_supprime");
				utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville, motDePasse, credit, administrateur, utilisateurDesactive, utilisateurSupprime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int identifiant) {
		try (Connection cnx = PoolConnection.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR)) {
			pstmt.setInt(1, identifiant);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
