package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

import org.springframework.ui.Model;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

	 @PostMapping("/login")
	    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {

	    	User user = userRepo.findByEmail(email);
	    	
	    	 if (user != null) {
	    		
	    		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	 	        if (passwordEncoder.matches(password, user.getPassword())) {

	 	            if(user.isAdmin()) {
	 	            	
	 	            	return "index";
	 	            	
	 	            }else {

		 	        	model.addAttribute("error", "Vous n'avez pas l'acces admin.");
		 	            return "login";	
	 	            }
	 	        } else {
	 	        	
	 	        	model.addAttribute("error", "Mot de passe incorrect.");
	 	            return "login";	 
	 	            }
	    	 }
	            model.addAttribute("error", "Adresse email incorrect.");
	            return "login";
	    }
}
