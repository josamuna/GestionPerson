package com.josamuna.project.model;

import java.util.List;

public interface PersonDAO {
	void save(Person person);
	void update(Person person);
	void delete(Person person);
	Person findPersonById(int id);
	Person findPersonByName(String firstName);
	List<Person> findPersons();
}
