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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TypePointGeographique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String nom;
	@Column(nullable = false, unique = true)
	@NotNull
	private String code;
	@OneToMany(mappedBy = "type")
	private List<PointGeographique> pointGeographiques;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private User userCreate;
	@ManyToOne
	private User userUpdate;
	@Column(updatable = false)
	private Date dateCreate; 
	
	public TypePointGeographique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypePointGeographique(@NotNull String nom, @NotNull String code) {
		super();
		this.nom = nom;
		this.code = code;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<PointGeographique> getPointGeographiques() {
		return pointGeographiques;
	}

	public void setPointGeographiques(List<PointGeographique> pointGeographiques) {
		this.pointGeographiques = pointGeographiques;
	}

	private Date dateUpdate;

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
		return "TypePointGeographique [nom=" + nom + ", code=" + code + "]";
	}

	
}
