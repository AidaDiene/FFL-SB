package sn.sonatel.ci_diourbel.fiber_failure_locator.webServiceRest;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;

public class JsonDeserialize {

	private double distance;
	private Itineraire iti;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Itineraire getIti() {
		return iti;
	}

	public void setIti(Itineraire iti) {
		this.iti = iti;
	}
	
	
}
