package sn.sonatel.ci_diourbel.fiber_failure_locator.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;

public interface ItineraireRepository extends JpaRepository<Itineraire, Long> {

}
