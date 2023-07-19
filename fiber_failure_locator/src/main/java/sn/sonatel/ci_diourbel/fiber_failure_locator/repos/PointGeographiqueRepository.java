package sn.sonatel.ci_diourbel.fiber_failure_locator.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;

public interface PointGeographiqueRepository extends JpaRepository<PointGeographique, Long> {

	@Query("select pg from PointGeographique pg where pg.typePointGeographique = :tpg")
	List<PointGeographique> findByTypePointGeographique(@Param("tpg") TypePointGeographique tpg);
}
