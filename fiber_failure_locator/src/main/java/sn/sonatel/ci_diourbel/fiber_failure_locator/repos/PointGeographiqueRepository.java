package sn.sonatel.ci_diourbel.fiber_failure_locator.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;

public interface PointGeographiqueRepository extends JpaRepository<PointGeographique, Long> {

}
