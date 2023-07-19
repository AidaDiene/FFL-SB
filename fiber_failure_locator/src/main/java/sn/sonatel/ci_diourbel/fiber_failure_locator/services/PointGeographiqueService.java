package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.util.List;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;

public interface PointGeographiqueService {

	List<PointGeographique> findAll();
	List<PointGeographique> findByTypePointGeographique(TypePointGeographique tpg);
}
