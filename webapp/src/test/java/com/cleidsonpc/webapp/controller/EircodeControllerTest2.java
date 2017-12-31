package com.cleidsonpc.webapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cleidsonpc.webapp.WebappApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebappApplication.class)
@WebAppConfiguration
public class EircodeControllerTest2 {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void notFound() throws Exception {
		mockMvc.perform(get("http://localhost:8080/webapp/home/notfound"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testEirCodeSearch() throws Exception {
		mockMvc.perform(get("http://localhost:8080/webapp/search_eircode?ec=NR147PZ"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].addressline1", is("Allies Computing Ltd")))
		.andExpect(jsonPath("$[0].addressline2", is("Manor Farm Barns, Fox Road")))
		.andExpect(jsonPath("$[0].summaryline", is("Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ")))
		.andExpect(jsonPath("$[0].organisation", is("Allies Computing Ltd")))
		.andExpect(jsonPath("$[0].street", is("Fox Road")))
		.andExpect(jsonPath("$[0].posttown", is("Norwich")))
		.andExpect(jsonPath("$[0].county", is("Norfolk")))
		.andExpect(jsonPath("$[0].postcode", is("NR14 7PZ")));
	}

}
