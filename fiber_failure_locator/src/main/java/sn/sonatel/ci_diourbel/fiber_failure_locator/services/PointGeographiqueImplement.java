package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointGeographiqueRepository;

@Service
public class PointGeographiqueImplement implements PointGeographiqueService {

	@Autowired
	PointGeographiqueRepository pgRepository;
	
	@Override
	public List<PointGeographique> findByTypePointGeographique(TypePointGeographique tpg) {
		return pgRepository.findByTypePointGeographique(tpg);
	}

	@Override
	public List<PointGeographique> findAll() {
		return pgRepository.findAll();
	}

}
