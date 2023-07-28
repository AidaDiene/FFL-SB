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

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.TypePointGeographique;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.TypePointGeographiqueRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Controller
@RequestMapping("/typePointGeographique")
public class TypePointGeographiqueController {

	@Autowired
	TypePointGeographiqueRepository tpgRepo;
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String getAllTypePointGeographique(Model model)
	{
		List<TypePointGeographique> tpgList = tpgRepo.findAll();
	    model.addAttribute("tpgs", tpgList);
	    return "typePointGeographique/list-tpg";
	}
	
	@GetMapping("/showNewTypePointGeographiqueForm")
	public String showNewTypePointGeographiqueForm()
	{
	    return "typePointGeographique/new-tpg";
	}
	
	@PostMapping("/saveTypePointGeographique")
	public String saveTypePointGeographique(@ModelAttribute("type_point_geographique") TypePointGeographique tpg, Principal principal)
	{
		User userCreate = userRepo.findByEmail(principal.getName());
		tpg.setUserCreate(userCreate);
		tpg.setDateCreate(new Date());
		tpgRepo.save(tpg);
		return "redirect:/typePointGeographique/"; 
	}
	
	@GetMapping("/showTypePointGeographique/{id}")
	public String showTypePointGeographique(@PathVariable("id") Long id, Model model)
	{
		TypePointGeographique tpg = tpgRepo.findById(id).get();
	    model.addAttribute("tpg", tpg);
	    return "typePointGeographique/show-tpg";
	}
	
    @GetMapping("/showTypePointGeographiqueEditFrom/{id}")
    public String showTypePointGeographiqueEditFrom(@PathVariable("id") Long id, Model model) {
    	TypePointGeographique tpg = tpgRepo.findById(id).orElse(null);
    	model.addAttribute("tpg", tpg);
    	return "typePointGeographique/edit-tpg";
    }
    
    @PostMapping("/updateTypePointGeographique/{id}")
	public String updateTypePointGeographique(@PathVariable("id") long id, @Valid TypePointGeographique tpg, BindingResult result, Principal principal)
	{
    	if (result.hasErrors()) {
            tpg.setId(id);
            return "typePointGeographique/edit-tpg";
        }
    	User userUpdate = userRepo.findByEmail(principal.getName());
    	tpg.setUserUpdate(userUpdate);
    	tpg.setDateUpdate(new Date());
    	tpgRepo.save(tpg);
		return "redirect:/typePointGeographique/"; 
	}

	@GetMapping("/deleteTypePointGeographique/{id}")
	public String deleteTypePointGeographique(@PathVariable("id") Long id, Model model)
	{
		tpgRepo.deleteById(id);
		List<TypePointGeographique> tpgList =  tpgRepo.findAll();
	    model.addAttribute("tpgs", tpgList);
	    return "typePointGeographique/list-tpg";
	}
}
