package sn.sonatel.ci_diourbel.fiber_failure_locator.entities;

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

@Entity
public class PointGeographique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String nom;
	@Column(nullable = false)
	@NotNull
	private Double latitude;
	@Column(nullable = false)
	@NotNull
	private Double longitude;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private TypePointGeographique typePointGeographique;
	@ManyToMany(mappedBy = "pointGeographiques")
    private List<Itineraire> itineraires;
	@ManyToOne
	@JoinColumn(nullable = false, updatable = false)
	@NotNull
	private User userCreate;
	@ManyToOne
	private User userUpdate;
	@Column(nullable = false, updatable = false)
	private Date dateCreate; 
	private Date dateUpdate;
	
	public PointGeographique() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PointGeographique(String nom, Double latitude, Double longitude, TypePointGeographique typePointGeographique) {
		super();
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.typePointGeographique = typePointGeographique;
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public TypePointGeographique getTypePointGeographique() {
		return typePointGeographique;
	}
	public void setTypePointGeographique(TypePointGeographique typePointGeographique) {
		this.typePointGeographique = typePointGeographique;
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
		return "PointGeographique [nom=" + nom + ", latitude=" + latitude + ", longitude=" + longitude + ", typePointGeographique="
				+ typePointGeographique + "]";
	}
	
	
}
