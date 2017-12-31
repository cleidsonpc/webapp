package com.cleidsonpc.webapp.model;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	public Person findById(Long id);
	
}
