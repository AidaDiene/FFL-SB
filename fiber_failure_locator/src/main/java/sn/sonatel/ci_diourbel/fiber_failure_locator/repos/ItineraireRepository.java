package sn.sonatel.ci_diourbel.fiber_failure_locator.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;

import java.util.List;

public interface ItineraireRepository extends JpaRepository<Itineraire, Long> {

    @Query("select iti from Itineraire iti where iti.depart.id = :departId and iti.arrivee.id = :arriveeId")
    Itineraire findByIdDepartIdArrivee(
            @Param("departId") Long departId,
            @Param("arriveeId") Long arriveeId);

}
