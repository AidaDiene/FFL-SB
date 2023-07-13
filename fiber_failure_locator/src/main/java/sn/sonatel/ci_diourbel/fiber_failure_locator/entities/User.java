package sn.sonatel.ci_diourbel.fiber_failure_locator.entities;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String prenom;
	@Column(nullable = false)
	@NotNull
	private String nom;
	@Column(nullable = false, unique = true)
	@NotNull
	private String matricule;
	private String telephone;
	@Column(nullable = false, unique = true)
	@NotNull
	private String email;
	@Column(nullable = false)
	@NotNull
	private String password;
	private boolean isAdmin;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String prenom, String nom, String matricule, String telephone, String email, String password,
			boolean isAdmin) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.matricule = matricule;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [prenom=" + prenom + ", nom=" + nom + ", matricule=" + matricule + ", telephone=" + telephone
				+ ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	
}
