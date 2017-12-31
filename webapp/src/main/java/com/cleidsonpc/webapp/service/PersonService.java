package com.cleidsonpc.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleidsonpc.webapp.model.Person;
import com.cleidsonpc.webapp.model.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person save(final Person person) {
		Person personAdded = personRepository.save(person);
		return personAdded;
	}
	
	public Person update(final Person person) {
		Person personUpdated = personRepository.save(person);
		return personUpdated;
	}
	
	public Iterable<Person> findAll() {
		Iterable<Person> personList = personRepository.findAll();
		return personList;
	}
	
	public Person findById(Long id) {
		Person person = personRepository.findById(id);
		return person;
	}
	
	public void delete(final Person person) {
		personRepository.delete(person);
	}
	
}
