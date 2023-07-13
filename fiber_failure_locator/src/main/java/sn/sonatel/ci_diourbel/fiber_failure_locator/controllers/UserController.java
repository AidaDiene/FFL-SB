package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/newUser")
	public String showCreate()
	{
	return "user/new-user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user)
	{
		userRepo.save(user);
		return "user/new-user"; 
	}
}
