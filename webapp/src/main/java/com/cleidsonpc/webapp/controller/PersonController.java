package com.cleidsonpc.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleidsonpc.webapp.model.Person;
import com.cleidsonpc.webapp.service.PersonService;

@Controller
@RequestMapping(value="/webapp/persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Person person, Model model) {
		personService.save(person);
		return "person_page";
	}
	
//	@RequestMapping(method=RequestMethod.PUT)
//	public void update(@RequestBody Person person) {
//		personService.update(person);
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String findAll(Model model) {
		Iterable<Person> personList = personService.findAll();
		model.addAttribute("personList", personList);
		return "person_page";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET )
	public String findById(@PathVariable("id") Long id, Model model) {
		Person person = personService.findById(id);
		
		if(person != null)
			model.addAttribute("person", person);
		
		return "person_page";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET, params={"idPerson"}) //params={"idPerson"} => ?idPerson=1
	public String findById2(@RequestParam("idPerson") Long id, Model model) {
		Person person = personService.findById(id);
		model.addAttribute("person", person);
		return "person_page";
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody Person person) {
		personService.delete(person);
	}
	
}
