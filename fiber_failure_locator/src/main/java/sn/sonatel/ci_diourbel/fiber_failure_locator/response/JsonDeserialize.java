package sn.sonatel.ci_diourbel.fiber_failure_locator.response;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;

public class JsonDeserialize {

	private double distance;
	private Long idCentral;
	private Long idSite;
	private String provenance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Long getIdCentral() {
		return idCentral;
	}

	public void setIdCentral(Long idCentral) {
		this.idCentral = idCentral;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public String getProvenance(){return provenance;}

	public void setProvenance(String provenance){ this.provenance = provenance;}
	
}
