package sn.sonatel.ci_diourbel.fiber_failure_locator.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;

public interface TypePointGeographiqueRepository extends JpaRepository<TypePointGeographique, Long> {

    TypePointGeographique findByCode(String code);
}
