package com.cleidsonpc.webapp.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cleidsonpc.webapp.WebappApplication;
import com.cleidsonpc.webapp.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebappApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Before
	public void before() {
		
	}

	@Test
	public void test1Save() throws Exception {
		Person person = new Person();
		person.setName("Test Name");
		person.setAddress("Test Address");
		person.setPhone(11112222);
		
		Person newPerson = personService.save(person);
		Assert.assertNotNull(newPerson);
	}

	@Test
	public void test2FindByIdBeforeUpdate() throws Exception {
		Person person = personService.findById(1L);
		Assert.assertNotNull(person);
	}

	@Test
	public void test3Update() throws Exception {
		
		Person person = personService.findById(1L);
		person.setName("Test Name Updated");
		person.setAddress("Test Address Updated");
		person.setPhone(33334444);
		
		Person updatedPerson = personService.save(person);
		Assert.assertNotEquals("Test Name", updatedPerson.getName());
		Assert.assertNotEquals("Test Address", updatedPerson.getAddress());
		Assert.assertNotEquals(11112222, updatedPerson.getPhone());
		Assert.assertEquals("Test Name Updated", updatedPerson.getName());
		Assert.assertEquals("Test Address Updated", updatedPerson.getAddress());
		Assert.assertEquals(33334444, updatedPerson.getPhone());
	}

	@Test
	public void test4FindByIdAfterUpdate() throws Exception {
		Person person = personService.findById(1L);
		Assert.assertNotNull(person);
	}

	@Test
	public void test5FindAll() throws Exception {
		Iterable<Person> personList = personService.findAll();
		Assert.assertTrue(personList.iterator().hasNext());
	}

	@Test
	public void test6Delete() {
		Person person = personService.findById(1L);
		personService.delete(person);
		
		Person deletedPerson = personService.findById(1L);
		Assert.assertNull(deletedPerson);
	}
	
}
