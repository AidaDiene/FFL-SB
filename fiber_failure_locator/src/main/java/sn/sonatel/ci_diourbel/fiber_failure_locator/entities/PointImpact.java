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

@Entity
public class PointImpact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private Double latitude;
	@Column(nullable = false)
	@NotNull
	private Double longitude;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Itineraire itineraire;
	@ManyToMany
	private List<Drone> drones = new ArrayList<>();
	//@ManyToOne
	//@JoinColumn(nullable = false)
	//@NotNull
	//private User user;
	@Column(nullable = false)
	@NotNull
	private Date dateRecherche;
	
	public PointImpact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointImpact(Double latitude, Double longitude, Itineraire itineraire) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.itineraire = itineraire;
	}

	public PointImpact(Double latitude, Double longitude, Itineraire itineraire, List<Drone> drones, /*User user,*/
			Date dateRecherche) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.itineraire = itineraire;
		this.drones = drones;
		//this.user = user;
		this.dateRecherche = dateRecherche;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Itineraire getItineraire() {
		return itineraire;
	}

	public void setItineraire(Itineraire itineraire) {
		this.itineraire = itineraire;
	}

	public List<Drone> getDrones() {
		return drones;
	}

	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

/*	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} */

	public Date getDateRecherche() {
		return dateRecherche;
	}

	public void setDateRecherche(Date dateRecherche) {
		this.dateRecherche = dateRecherche;
	}

	@Override
	public String toString() {
		return "PointImpact [latitude=" + latitude + ", longitude=" + longitude + ", itineraire=" + itineraire
				+ ", drones=" + drones + ", user=" + /*user + */ ", dateRecherche=" + dateRecherche + "]";
	} 
	
}
