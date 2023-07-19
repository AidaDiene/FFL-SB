package sn.sonatel.ci_diourbel.fiber_failure_locator.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "itineraires")
@Entity
public class Itineraire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String nom;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private PointGeographique depart;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private PointGeographique arrivee;
	@ManyToMany
	private List<PointGeographique> pointGeographiques = new ArrayList<>();
	@OneToMany(mappedBy = "itineraire")
	private List<PointImpact> pointImpact;
	//@ManyToOne
	//@JoinColumn(nullable = false)
	//@NotNull
	//private User userCreate;
	@ManyToOne
	private User userUpdate;
	@Column(updatable = false)
	private Date dateCreate; 
	private Date dateUpdate;
	
	public Itineraire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Itineraire(String nom, PointGeographique depart, PointGeographique arrivee) {
		super();
		this.nom = nom;
		this.depart = depart;
		this.arrivee = arrivee;
	}

	public Itineraire(String nom, PointGeographique depart, PointGeographique arrivee,
			List<PointGeographique> pointGeographiques) {
		super();
		this.nom = nom;
		this.depart = depart;
		this.arrivee = arrivee;
		this.pointGeographiques = pointGeographiques;
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

	public PointGeographique getDepart() {
		return depart;
	}

	public void setDepart(PointGeographique depart) {
		this.depart = depart;
	}

	public PointGeographique getArrivee() {
		return arrivee;
	}

	public void setArrivee(PointGeographique arrivee) {
		this.arrivee = arrivee;
	}

	public List<PointGeographique> getPointGeographiques() {
		return pointGeographiques;
	}

	public void setPointGeographiques(List<PointGeographique> pointGeographiques) {
		this.pointGeographiques = pointGeographiques;
	}

/*	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	} */

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
		return "Itineraire [nom=" + nom + ", depart=" + depart + ", arrivee=" + arrivee + ", pointGeographiques="
				+ pointGeographiques + "]";
	}

	
}
