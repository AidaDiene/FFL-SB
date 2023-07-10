package sn.sonatel.ci_diourbel.fiber_failure_locator.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TypePointGeographique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "type")
	private List<PointGeographique> pointGeographiques;
	@ManyToOne
	private User userCreate;
	@ManyToOne
	private User userUpdate;
	private Date dateCreate; 
	private Date dateUpdate;
	
	public TypePointGeographique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypePointGeographique(String nom) {
		super();
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public User getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(User userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Override
	public String toString() {
		return "TypePointGeographique [nom=" + nom + "]";
	}
	
}
