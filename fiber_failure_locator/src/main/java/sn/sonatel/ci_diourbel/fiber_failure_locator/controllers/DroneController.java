package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Drone;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.DroneRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Controller
@RequestMapping("/drones")
public class DroneController {

	@Autowired
	DroneRepository droneRepo;
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String getAllDrones(Model model)
	{
		List<Drone> droneList = droneRepo.findAll();
	    model.addAttribute("drones", droneList);
	    return "drone/list-drone";
	}
	
	@GetMapping("/showNewDroneForm")
	public String showNewDroneForm()
	{
	return "drone/new-drone";
	}
	
	@PostMapping("/saveDrone")
	public String saveDrone(@ModelAttribute("drone") Drone drone, Principal principal)
	{
		User userCreate = userRepo.findByEmail(principal.getName());
		drone.setUserCreate(userCreate);
		drone.setDateCreate(new Date());
		droneRepo.save(drone);
		return "redirect:/drones/"; 
	}
	
	@GetMapping("/showDrone/{id}")
	public String showDrone(@PathVariable("id") Long id, Model model)
	{
		Drone drone = droneRepo.findById(id).get();
	    model.addAttribute("drone", drone);
	    return "drone/show-drone";
	}
	
    @GetMapping("/showDroneEditFrom/{id}")
    public String showDroneEditFrom(@PathVariable("id") Long id, Model model) {
    	Drone drone = droneRepo.findById(id).orElse(null);
    	model.addAttribute("drone", drone);
    	return "drone/edit-drone";
    }
    
    @PostMapping("/updateDrone/{id}")
	public String updateDrone(@PathVariable("id") long id, Drone drone, BindingResult result, Principal principal)
	{
    	if (result.hasErrors()) {
            drone.setId(id);
            return "drone/edit-drone";
        }
    	User userUpdate = userRepo.findByEmail(principal.getName());
		drone.setUserUpdate(userUpdate);
		drone.setDateUpdate(new Date());
    	droneRepo.save(drone);
		return "redirect:/drones/"; 
	}

	@GetMapping("/deleteDrone/{id}")
	public String deleteDrone(@PathVariable("id") Long id, Model model)
	{
		droneRepo.deleteById(id);
		List<Drone> droneList = droneRepo.findAll();
	    model.addAttribute("drones", droneList);
	    return "drone/list-drone";
	}
}
