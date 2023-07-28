package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Principal principal)
	{
		System.out.println(principal.getName());
	    return "index";
	}
}
