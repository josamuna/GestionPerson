package com.josamuna.project.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.josamuna.project.accessdata.MySQLPersonDAO;
import com.josamuna.project.model.Person;

class MySQLPersonDAOTest {

	private static MySQLPersonDAO dao;
	private static Person testPerson;

	// Database variables
	private static final String url = "jdbc:mysql://localhost:3306/gestion_person";
	private static final String user = "root";
	private static final String dbPassword = "";
	private static final String driver = "com.mysql.cj.jdbc.Driver";

	@BeforeAll
	static void setUpBeforeAll() {
		try {
			Class.forName(driver);
			dao = new MySQLPersonDAO();
			assertNotNull(dao);
			System.out.println("Driver found!");
		} catch (ClassNotFoundException ex) {
			fail("Driver not found!, " + ex.getMessage());
		}
	}

	@BeforeEach
	void getConnectionTest(TestInfo info) {
		try (Connection con = DriverManager.getConnection(url, user, dbPassword)) {
			assertNotNull(con);
			assertFalse(con.isClosed());
			System.out.println(info.getDisplayName());
		} catch (SQLException ex) {
			fail("Failed to open database connection, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Save Person")
	@Order(1)
	void saveTest() {
		try {
			Person person = new Person("Franck", "Kubala", 20, "kubala@gmail.com", "P@ssword");
			dao.save(person);
			assertTrue(person.getId() > 0);
			testPerson = person;
		} catch (SQLException ex) {
			fail("Failed to save Person, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Update Person")
	@Order(2)
	void updateTest() {
		try {
			testPerson.setFirstName("Francki");
			testPerson.setLastName("Zawadi");
			testPerson.setAge(25);
			testPerson.setEmail("zawadi@gmail.com");
			testPerson.setPassword("Pwd");
			dao.update(testPerson);

			// Check updated person
			Person personUpdated = dao.findPersonById(testPerson.getId());
			assertEquals("Francki", personUpdated.getFirstName());
			assertEquals("Zawadi", personUpdated.getLastName());
			assertEquals(25, personUpdated.getAge());
			assertEquals("zawadi@gmail.com", personUpdated.getEmail());
			assertEquals("Pwd", personUpdated.getPassword());

		} catch (SQLException ex) {
			fail("Failed to update Person, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find Person by ID")
	@Order(3)
	void findPersonByIdTest() {
		try {
			Person person = dao.findPersonById(testPerson.getId());
			assertEquals(person.getId(), testPerson.getId());
		} catch (SQLException ex) {
			fail("Failed to find Person by ID, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find Person by name")
	@Order(4)
	void findPersonByNameTest() {
		try {
			Person person = dao.findPersonByName(testPerson.getFirstName());
			assertEquals(person.getFirstName(), testPerson.getFirstName());
		} catch (SQLException ex) {
			fail("Failed to find Person by name, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find all Persons")
	@Order(5)
	void findPersons() throws Exception {
		try {
			List<Person> persons = new ArrayList<>();
			persons = dao.findPersons();
			assertFalse(persons.isEmpty());
		} catch (SQLException ex) {
			fail("Failed to find Persons, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Delete Person")
	@Order(6)
	void deleteTest() {
		try {
			dao.delete(testPerson);

			Person personDeleted = dao.findPersonById(testPerson.getId());
			assertNull(personDeleted);
		} catch (SQLException ex) {
			fail("Failed to delete Person, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Delete inexisting Person")
	@Order(7)
	void deleteFakePerson() {
		Person fakePerson = new Person(0, "Fake_firstname", "Fake_lastname", 200, "fakeemail@fake.com",
				"fake_password");

		assertThrows(IllegalArgumentException.class, () -> dao.delete(fakePerson));
	}

}
