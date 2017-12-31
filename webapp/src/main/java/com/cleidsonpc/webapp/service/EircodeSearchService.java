package com.cleidsonpc.webapp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cleidsonpc.webapp.model.Address;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Class responsible for do the communication between the web application and external web service.
 * Documentation.
 * 	https://developers.alliescomputing.com/postcoder-web-api/address-lookup/eircode
 * 	https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise
 */
@Service
public class EircodeSearchService {
	private static final Logger LOG = Logger.getLogger(EircodeSearchService.class);
	private final static RestTemplate restTemplate = new RestTemplate();
	
	@Value("${params.cleidsonpc.ws.id}")
    private String wsId;
	
	private static final String URL_EIR_CODE_SERVICE = "http://ws.postcoder.com/pcw/%s/street/uk/%s?format=json&lines=3";
	
	/**
	 * Main method to call the web service (ws.postcoder.com) and retrieve the address by eircode.
	 * @param eircode - ({@link String})
	 * @return The answer with the complete address as JSON 
	 */
	@Cacheable("eirCodeCache") // Avoids unnecessary calls to eirCode web service
	@HystrixCommand(fallbackMethod="errorService")
	public EircodeServiceResponse searchEirCode(String eircode) {
		
		LOG.debug("EirCodeSearch.searchEirCode called.");
		
		EircodeServiceResponse response = new EircodeServiceResponse();
		
		String newEirCodeURL = String.format(URL_EIR_CODE_SERVICE, wsId, eircode); // Build the URL to the web service of search.
        Address[] wsRes = restTemplate.getForObject(newEirCodeURL, Address[].class); // Call the ws.postcoder.com web service.
		response.setAddressList(wsRes);
        
        if(wsRes.length == 0) {
			response.setAlertMessage("Nothing was returned, try again.");
        }
        
		return response;
	}
	
	/**
	 * Method called if the link to the web service ws.postcoder.com is broken.
	 */
	public EircodeServiceResponse errorService(@RequestParam("ec") String eircode, Throwable t) {
		LOG.error("Hystrix fallbackMethod: " + t.getMessage());
		
		EircodeServiceResponse response = new EircodeServiceResponse();
		response.setErrorMessage("The web service ws.postcoder.com is broken");
		
		return response;
	}
	
}
