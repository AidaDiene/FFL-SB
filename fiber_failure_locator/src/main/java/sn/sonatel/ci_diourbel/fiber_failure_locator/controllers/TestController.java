package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController implements ErrorController {

	@GetMapping("/liste")
	public String showCreate()
	{
	return "liste";
	}
	
	
}
