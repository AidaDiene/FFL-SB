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

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.Drone;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.DroneRepository;

@Controller
@RequestMapping("/drones")
public class DroneController {

	@Autowired
	DroneRepository droneRepo;
	
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
	public String saveUser(@ModelAttribute("drone") Drone drone)
	{
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
	public String updateDrone(@PathVariable("id") long id, @Valid Drone drone, BindingResult result)
	{
    	if (result.hasErrors()) {
            drone.setId(id);
            return "drone/edit-drone";
        }
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
