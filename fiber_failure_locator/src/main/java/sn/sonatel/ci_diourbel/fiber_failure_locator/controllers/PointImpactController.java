package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.PointImpact;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.PointImpactRepository;

@Controller
@RequestMapping("/pointImpact")
public class PointImpactController {

	@Autowired
	PointImpactRepository piRepo;
	
	
	@GetMapping("/")
	public String getAllPointImpact(Model model)
	{
		List<PointImpact> piList = piRepo.findAll();
	    model.addAttribute("pis", piList);
	    return "pointImpact/list-pi";
	}
	
	@GetMapping("/showPointImpact/{id}")
	public String showPointImpact(@PathVariable("id") Long id, Model model)
	{
		PointImpact pi = piRepo.findById(id).get();
	    model.addAttribute("pi", pi);
	    return "pointImpact/show-pi";
	}
}
