package com.cleidsonpc.webapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest {

	private static final String URL_SERVICE = "http://localhost:8080/webapp/persons";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test1Save() throws Exception {
		mockMvc.perform(post(URL_SERVICE).flashAttr("person", "{name:\"Test Name\", address:\"Test Address\", phone:\"11112222\"}"))
				.andExpect(status().isOk());
		
		// .andExpect(redirectedUrl("person_page"))
		// .andExpect(model().size(1))
		// .andExpect(flash().attribute("message", "success!"));

	}

	@Test
	public void test2FindByIdBeforeUpdate() throws Exception {
		mockMvc.perform(get(URL_SERVICE + "/1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("person"));
	}

	@Test
	public void test3Update() throws Exception {
		mockMvc.perform(post(URL_SERVICE).param("person", "{id:\"1\", name:\"Test Name updated\", address:\"Test Address updated\", phone:\"33334444\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void test4FindByIdAfterUpdate() throws Exception {
		mockMvc.perform(get(URL_SERVICE + "/1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("person"));
	}

	@Test
	public void test5FindAll() throws Exception {
		mockMvc.perform(get(URL_SERVICE))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("personList"));
	}

	@Test
	public void test6FindByIdSecondMethod() throws Exception {
		mockMvc.perform(get(URL_SERVICE + "/?idPerson=1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("person"));
	}

	@Test
	public void test7Delete() {

	}
}
