package com.cleidsonpc.webapp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.cleidsonpc.webapp.service.EircodeSearchService;
import com.cleidsonpc.webapp.service.EircodeServiceResponse;

/**
 * Class responsible for control the information about eircode search.
 */
@Controller
@RequestMapping(value="/webapp/eircodes")
public class EircodeController {

	private static final Logger LOG = Logger.getLogger(EircodeController.class);
	
	@Autowired
	private EircodeSearchService eirCodeSearchService;
	
	@RequestMapping("/*")
	public String page(Model model) {        
        return "eircode_page";
	}
	
	/**
	 * Make the link to call the web service to retrieve the address of the eircode.
	 * @param eircode - ({@link String})
	 * @return The same answer of the {@link EircodeSearchService#searchEirCode(String)}
	 */
	@ResponseBody
	@RequestMapping(value="/find", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public EircodeServiceResponse searchEirCode(@RequestParam("ec") String eircode) {
		EircodeServiceResponse response = new EircodeServiceResponse();
		
		if(StringUtils.isEmpty(eircode) || "undefined".equals(eircode)) {
			LOG.debug("Eircode is invalid");
			response.setErrorMessage("Eircode is invalid");
			
		} else {
			LOG.debug("Home.searchEirCode called.");
			
			LOG.debug("It will seek the eircode.");
			response = eirCodeSearchService.searchEirCode(eircode); // Call the EirCodeSearch service.
			
			LOG.debug("Response: " + response);
		}
		
		return response;
	}
}
