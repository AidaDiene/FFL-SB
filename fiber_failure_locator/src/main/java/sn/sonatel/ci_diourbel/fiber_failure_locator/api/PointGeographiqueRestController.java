package sn.sonatel.ci_diourbel.fiber_failure_locator.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointGeographiqueRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.TypePointGeographiqueRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PointGeographiqueRestController {
    private final PointGeographiqueRepository pgRepo;
    private final TypePointGeographiqueRepository tpgRepo;
    public PointGeographiqueRestController(PointGeographiqueRepository repository, TypePointGeographiqueRepository tpgRepo) {
        this.pgRepo = repository;
        this.tpgRepo = tpgRepo;
    }
    @GetMapping("/centraux")
    List<PointGeographique> allCentral(){
        TypePointGeographique type = this.tpgRepo.findByCode("CENTRAL");
        return this.pgRepo.findByTypePointGeographique(type);
    }
    @GetMapping("/sites")
    List<PointGeographique> allSite(){
        TypePointGeographique type = this.tpgRepo.findByCode("SITE");
        return this.pgRepo.findByTypePointGeographique(type);
    }
    @GetMapping("/chambres")
    List<PointGeographique> allChambre(){
        TypePointGeographique type = this.tpgRepo.findByCode("CHAMBRE");
        return this.pgRepo.findByTypePointGeographique(type);
    }
    @GetMapping("/sections")
    List<PointGeographique> allSECTION(){
        TypePointGeographique type = this.tpgRepo.findByCode("SECTION");
        return this.pgRepo.findByTypePointGeographique(type);
    }
}
