package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Itineraire;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.ItineraireRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointGeographiqueRepository;

@Controller
@RequestMapping("/itineraires")
public class ItineraireController {

	@Autowired
	ItineraireRepository itineraireRepo;
	@Autowired
	PointGeographiqueRepository pgRepo;
	
	@GetMapping("/")
	public String getAllItineraires(Model model)
	{
		List<Itineraire> itiList = itineraireRepo.findAll();
	    model.addAttribute("itineraires", itiList);
	    return "itineraire/list-itineraire";
	}
	
	@GetMapping("/showNewItineraireForm")
	public String showNewItineraireForm(Model model )
	{
		List<PointGeographique> pgList = pgRepo.findAll();
	    model.addAttribute("pgList", pgList);
	    return "itineraire/new-itineraire";
	}
	
	@PostMapping("/saveItineraire")
	public String saveItineraire(@ModelAttribute("itineraire") Itineraire itineraire)
	{
		itineraireRepo.save(itineraire);
		return "redirect:/itineraires/"; 
	}
	
	@GetMapping("/showItineraire/{id}")
	public String showItineraire(@PathVariable("id") Long id, Model model)
	{
		Itineraire itineraire = itineraireRepo.findById(id).get();
	    model.addAttribute("itineraire", itineraire);
	    return "itineraire/show-itineraire";
	}
	
    @GetMapping("/showItineraireEditFrom/{id}")
    public String showItineraireEditFrom(@PathVariable("id") Long id, Model model) {
    	Itineraire itineraire = itineraireRepo.findById(id).orElse(null);
    	model.addAttribute("itineraire", itineraire);
    	List<PointGeographique> pgList = pgRepo.findAll();
	    model.addAttribute("pgList", pgList);
    	return "itineraire/edit-itineraire";
    }
    
    @PostMapping("/updateItineraire/{id}")
	public String updateItineraire(@PathVariable("id") long id, @Valid Itineraire itineraire, BindingResult result)
	{
    	if (result.hasErrors()) {
    		itineraire.setId(id);
            return "itineraire/edit-itineraire";
        }
    	itineraireRepo.save(itineraire);
		return "redirect:/itineraires/"; 
	}

	@GetMapping("/deleteItineraire/{id}")
	public String deleteItineraire(@PathVariable("id") Long id, Model model)
	{
		itineraireRepo.deleteById(id);
		List<Itineraire> itineraireList =  itineraireRepo.findAll();
	    model.addAttribute("itineraires", itineraireList);
	    return "itineraire/list-itineraire";
	}
}
