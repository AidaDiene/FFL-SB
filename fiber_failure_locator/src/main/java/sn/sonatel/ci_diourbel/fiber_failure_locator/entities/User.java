package sn.sonatel.ci_diourbel.fiber_failure_locator.entities;

import java.io.File;

import javax.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "utilisateurs")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull(message = "Le prénom ne peut pas être vide")
	private String prenom;
	@Column(nullable = false)
	@NotNull(message = "Le nom ne peut pas être vide")
	private String nom;
	@Column(nullable = false, unique = true)
	@NotNull(message = "Le matricule ne peut pas être vide")
	private String matricule;
	private String telephone;
	@Column(nullable = false, unique = true)
	@NotNull(message = "L'email ne peut pas être vide")
	@Email(message = "L'email doit être valide")
	private String email;
	@Column(nullable = false)
	@NotNull
	private String password;
	@Column(nullable = false)
	private boolean isAdmin;
	@Column(nullable = false)
	private boolean enabled;
	private String photo;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String prenom, String nom, String matricule, String telephone, String email, String password,
			boolean isAdmin, boolean enabled) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.matricule = matricule;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.enabled = enabled;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [prenom=" + prenom + ", nom=" + nom + ", matricule=" + matricule + ", telephone=" + telephone
				+ ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + ", enabled=" + enabled + "]";
	}

	public static String encodePassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static boolean verifyPassword(String password, String encodedPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, encodedPassword);
	}

	public static void deleteFileNameExists(String directoryPath, String fileName) {
		File directory = new File(directoryPath);
		System.out.println(directory);
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File existingFile : files) {
		            System.out.println("Nom du fichier : " + existingFile.getName());
					if (existingFile.isFile()) {
						String existingFileName = existingFile.getName();
						System.out.println("Nom du fichier existing : " + existingFileName);
						if (existingFileName.startsWith(fileName)) {
				            System.out.println("Nom du fichier : ");

							if (existingFile.delete()) {
								System.out.println("Le fichier " + existingFileName + " a été supprimé avec succès.");
							} else {
								System.err.println("Impossible de supprimer le fichier " + existingFileName + ".");
							}
						}else {
				            System.out.println("Louy probleme bi  : ");
						}
					}
				}
			}
		}
	}
}
