package sn.sonatel.ci_diourbel.fiber_failure_locator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointImpact;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.ItineraireRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointImpactRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.response.JsonDeserialize;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PointImpactRestController {

    @Autowired
    ItineraireRepository itiRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    PointImpactRepository piRepo;
    @PostMapping("/recherche/point/impact")
    public PointImpact recherchePointImpact(@RequestBody JsonDeserialize request /*,Principal principal*/) {

        Itineraire iti = itiRepo.findByIdDepartIdArrivee(request.getIdCentral(), request.getIdSite());
        Double distance = request.getDistance();
        PointImpact pointImpact = new PointImpact(0.0, 0.0);
        List<PointGeographique> pointGeographiques = iti.getPointGeographiques();
        Double disTemp = 0.0;
        for (int i = 0; i < pointGeographiques.size() - 1; i++) {
            disTemp = disTemp + distanceBetween(pointGeographiques.get(i), pointGeographiques.get(i + 1));
            if (disTemp >= distance) {
                if (disTemp == distance) {
                    pointImpact = new PointImpact(pointGeographiques.get(i).getLatitude(),
                            pointGeographiques.get(i).getLongitude());
                } else {
                    // Si la distance totale dépasse la distance recherchée, calculer les
                    // coordonnées du point recherché
                    double remainingDistance = disTemp - distance;
                    double angle = getBearing(pointGeographiques.get(i), pointGeographiques.get(i + 1));
                    PointGeographique destinatonPoint = destinationPoint(pointGeographiques.get(i), remainingDistance,
                            angle);
                    pointImpact = new PointImpact(destinatonPoint.getLatitude(), destinatonPoint.getLongitude());
                }
                break;
            }
        }
        pointImpact.setDateRecherche(new Date());
        /*
         * if (iti.isPresent()) { Itineraire itin = iti.get();
         * pointImpact.setItineraire(itin); }
         */
        //pointImpact.setItineraire(iti.get());
        //User user = userRepo.findByEmail(principal.getName());
        //pointImpact.setUser(user);
        //piRepo.save(pointImpact);
        return pointImpact;
    }

    double distanceBetween(PointGeographique start, PointGeographique end) {
        int R = 6371; // rayon moyen de la Terre en kilomètres
        double dLat = _toRadians(end.getLatitude() - start.getLatitude());
        double dLon = _toRadians(end.getLongitude() - end.getLongitude());
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(_toRadians(start.getLatitude()))
                * Math.cos(_toRadians(end.getLatitude())) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    double _toRadians(Double degrees) {
        return degrees * Math.PI / 180;
    }

    PointGeographique destinationPoint(PointGeographique start, double distance, double angle) {
        int earthRadius = 6371000; // rayon de la Terre en mètres
        double lat1 = start.getLatitude() * Math.PI / 180.0;
        double lon1 = start.getLongitude() * Math.PI / 180.0;
        double brng = angle * Math.PI / 180.0;

        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance / earthRadius)
                + Math.cos(lat1) * Math.sin(distance / earthRadius) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(distance / earthRadius) * Math.cos(lat1),
                Math.cos(distance / earthRadius) - Math.sin(lat1) * Math.sin(lat2));

        return new PointGeographique(lat2 * 180.0 / Math.PI, lon2 * 180.0 / Math.PI);
    }

    double getBearing(PointGeographique start, PointGeographique end) {
        double lat1 = start.getLatitude() * Math.PI / 180.0;
        double lon1 = start.getLongitude() * Math.PI / 180.0;
        double lat2 = end.getLatitude() * Math.PI / 180.0;
        double lon2 = end.getLongitude() * Math.PI / 180.0;

        double y = Math.sin(lon2 - lon1) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1);
        double bearing = Math.atan2(y, x) * 180.0 / Math.PI;

        return bearing;
    }
}
