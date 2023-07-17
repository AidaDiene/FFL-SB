package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Controller
@RequestMapping("/utilisateurs")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String getAllUsers(Model model)
	{
		List<User> userList = userRepo.findAll();
	    model.addAttribute("users", userList);
	    return "user/list-user";
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm()
	{
	return "user/new-user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, HttpServletRequest request)
	{
	    String password = request.getParameter("password");
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(password);
	    user.setPassword(hashedPassword);
		userRepo.save(user);
		return "redirect:/utilisateurs/"; 
	}
	
	@GetMapping("/showUser/{id}")
	public String showUser(@PathVariable("id") Long id, Model model)
	{
		User user = userRepo.findById(id).get();
	    model.addAttribute("user", user);
	    return "user/show-user";
	}
	
    @GetMapping("/showUserEditFrom/{id}")
    public String showUserEditFrom(@PathVariable("id") Long id, Model model) {
    	User user = userRepo.findById(id).orElse(null);
    	model.addAttribute("user", user);
    	return "user/edit-user";
    }
    
    @PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user)
	{
		userRepo.save(user);
		return "redirect:/utilisateurs/"; 
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model)
	{
		userRepo.deleteById(id);
		List<User> userList = userRepo.findAll();
	    model.addAttribute("users", userList);
	    return "user/list-user";
	}
	
}
