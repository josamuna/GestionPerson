package com.josamuna.project.service;

import java.sql.SQLException;
import java.util.List;

import com.josamuna.project.model.Person;
import com.josamuna.project.model.PersonDAO;

public class PersonService {
	private final PersonDAO dao;

	public PersonService(PersonDAO dao) {
		this.dao = dao;
	}

	public void create(Person person) throws SQLException {
		dao.save(person);
	}

	public void update(Person person) throws SQLException {
		dao.update(person);
	}

	public void remove(Person person) throws SQLException {
		dao.delete(person);
	}

	public Person listPersonById(int id) throws SQLException {
		return dao.findPersonById(id);
	}

	public Person listPersonByName(String firstName) throws SQLException {
		return dao.findPersonByName(firstName);
	}

	public List<Person> listPersons() throws SQLException {
		return dao.findPersons();
	}
}
