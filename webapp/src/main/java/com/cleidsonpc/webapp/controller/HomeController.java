package com.cleidsonpc.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/webapp")
public class HomeController {

	/**
	 * Method responsible for open the main page.
	 * @param model - ({@link Model}) 
	 * @return The name of the page in the folder <b>"resources/templates"</b>
	 */
	@RequestMapping(value="/*")
	public String page(Model model) {        
        return "home_page";
	}
	
}
