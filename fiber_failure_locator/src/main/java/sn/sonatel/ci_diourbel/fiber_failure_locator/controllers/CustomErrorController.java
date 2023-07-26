package sn.sonatel.ci_diourbel.fiber_failure_locator.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

public class CustomErrorController implements ErrorController {

	 @GetMapping("/error")
	  @ResponseBody
	  String error(HttpServletRequest request) {
	    return "<h1>Error occurred</h1>";
	  }

	  public String getErrorPath() {
	    return "/error";
	  }
}
