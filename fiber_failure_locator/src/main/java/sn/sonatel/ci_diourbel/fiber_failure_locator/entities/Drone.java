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
import jakarta.persistence.Table;

@Table(name = "drones")
@Entity
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	@NotNull
	private String matricule;
	private String modele;
	@ManyToMany(mappedBy = "drones")
    private List<PointImpact> pointImpacts;
	//@ManyToOne
	//@JoinColumn(nullable = false)
	//@NotNull
	//private User userCreate;
	@ManyToOne
	private User userUpdate;
	@Column(updatable = false)
	private Date dateCreate; 
	private Date dateUpdate;
	
	public Drone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Drone(String matricule, String modele) {
		super();
		this.matricule = matricule;
		this.modele = modele;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

/*	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}
*/
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
		return "Drone [matricule=" + matricule + ", modele=" + modele + "]";
	}

}
