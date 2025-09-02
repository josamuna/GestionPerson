package com.josamuna.project.model;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
	void save(Person person) throws SQLException;

	void update(Person person) throws SQLException;

	void delete(Person person) throws SQLException;

	Person findPersonById(int id) throws SQLException;

	Person findPersonByName(String firstName) throws SQLException;

	List<Person> findPersons() throws SQLException;
}
