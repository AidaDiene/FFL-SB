package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.services.UserService;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userServ;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/monProfil")
	public String showProfil(Principal principal, Model model) {
		User user = userRepo.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "profil";
	}

	@GetMapping("/updatePassword")
	public String showChangeUserPassword() {
		return "change-password";
	}
	
	@PostMapping("/updatePassword")
	public String changeUserPassword(Principal principal, @RequestParam("password") String password,
			@RequestParam("current_password") String currentPassword, @RequestParam("password_confirmation") String passwordConfirmation, Model model) {

		User user = userRepo.findByEmail(principal.getName());

		if (user == null) {
			model.addAttribute("error", "Utilisateur non trouvé !");
			return "change-password";
		}
		if (!User.verifyPassword(currentPassword, user.getPassword())) {
			model.addAttribute("error", "Mot de passe actuel incorrecte !");
			return "change-password";
		}
		if (! password.equals(passwordConfirmation)) {
			model.addAttribute("error", "Le mot de passe et le confirme mot de passe doivent etre conforme !");
			return "change-password";
		}
		user.setPassword(User.encodePassword(password));
		userRepo.save(user);
		model.addAttribute("success", "Mot de passe modifié avec succés !");
		return "change-password";
	}

	  @PostMapping("/files/upload")
	  public String uploadFile(Model model, @RequestParam("file") MultipartFile file, Principal principal) {
	    String message = "";

	    try {
	       userServ.saveFile(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      model.addAttribute("message", message);
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
	      model.addAttribute("message", message);
	    }
	    User user =  userRepo.findByEmail(principal.getName());
	    model.addAttribute("user", user);
	    return "profil";
	  }
}
