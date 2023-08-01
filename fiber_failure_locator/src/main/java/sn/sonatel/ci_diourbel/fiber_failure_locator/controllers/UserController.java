package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Controller
@RequestMapping("/utilisateurs")
public class UserController implements WebMvcConfigurer {
	
	@Autowired
	UserRepository userRepo;
	
	/* @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/results").setViewName("results");
	    }*/

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
	public String saveUser( User user, BindingResult result, HttpServletRequest request)
	{
		if (result.hasErrors()) {
            return "user/new-user";
        }
        
	    String password = "passer123";
	    user.setPassword(User.encodePassword(password));
	    user.setEnabled(true);
	    if(request.getParameter("isAdmin") == null) {
    		user.setAdmin(false);
	    }else {
		    	user.setAdmin(true);
	    }
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
    
    @PostMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") long id, User user, BindingResult result, HttpServletRequest request)
	{
    	if (result.hasErrors()) {
            user.setId(id);
            return "user/edit-user";
        }
    	 if(request.getParameter("isAdmin") == null) {
     		user.setAdmin(false);
 	    }else {
 		    	user.setAdmin(true);
 	    }
    	 if(request.getParameter("enabled") == null) {
      		user.setEnabled(false);
  	    }else {
  		    	user.setEnabled(true);
  	    }
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
