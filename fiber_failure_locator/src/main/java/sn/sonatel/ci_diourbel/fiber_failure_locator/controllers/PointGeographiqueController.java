package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.security.Principal;
import java.util.Date;
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

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointGeographiqueRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.TypePointGeographiqueRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Controller
@RequestMapping("/pointGeographique")
public class PointGeographiqueController {

	@Autowired
	PointGeographiqueRepository pgRepo;
	@Autowired
	TypePointGeographiqueRepository tpgRepo;
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String getAllPointGeographique(Model model)
	{
		List<PointGeographique> pgList = pgRepo.findAll();
	    model.addAttribute("pgs", pgList);
	    return "pointGeographique/list-pg";
	}
	
	@GetMapping("/showNewPointGeographiqueForm")
	public String showNewPointGeographiqueForm(Model model )
	{
		List<TypePointGeographique> tpgList = tpgRepo.findAll();
	    model.addAttribute("tpgList", tpgList);
	    return "pointGeographique/new-pg";
	}
	
	@PostMapping("/savePointGeographique")
	public String savePointGeographique(@ModelAttribute("point_geographique") PointGeographique pg, Principal principal)
	{
		User userCreate = userRepo.findByEmail(principal.getName());
		pg.setUserCreate(userCreate);
		pg.setDateCreate(new Date());
		pgRepo.save(pg);
		return "redirect:/pointGeographique/"; 
	}
	
	@GetMapping("/showPointGeographique/{id}")
	public String showPointGeographique(@PathVariable("id") Long id, Model model)
	{
		PointGeographique pg = pgRepo.findById(id).get();
	    model.addAttribute("pg", pg);
	    return "pointGeographique/show-pg";
	}
	
    @GetMapping("/showPointGeographiqueEditFrom/{id}")
    public String showPointGeographiqueEditFrom(@PathVariable("id") Long id, Model model) {
    	PointGeographique pg = pgRepo.findById(id).orElse(null);
    	model.addAttribute("pg", pg);
    	List<TypePointGeographique> tpgList = tpgRepo.findAll();
	    model.addAttribute("tpgList", tpgList);
    	return "pointGeographique/edit-pg";
    }
    
    @PostMapping("/updatePointGeographique/{id}")
	public String updatePointGeographique(@PathVariable("id") long id, @Valid PointGeographique pg, BindingResult result, Principal principal)
	{
    	if (result.hasErrors()) {
            pg.setId(id);
            return "pointGeographique/edit-pg";
        }
    	User userUpdate = userRepo.findByEmail(principal.getName());
		pg.setUserUpdate(userUpdate);
		pg.setDateUpdate(new Date());
    	pgRepo.save(pg);
		return "redirect:/pointGeographique/"; 
	}

	@GetMapping("/deletePointGeographique/{id}")
	public String deletePointGeographique(@PathVariable("id") Long id, Model model)
	{
		pgRepo.deleteById(id);
		List<PointGeographique> pgList =  pgRepo.findAll();
	    model.addAttribute("pgs", pgList);
	    return "pointGeographique/list-pg";
	}
}
