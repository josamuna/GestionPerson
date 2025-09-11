package com.josamuna.project.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

import com.josamuna.project.accessdata.MySQLPersonDAO;
import com.josamuna.project.model.Person;
import com.josamuna.project.service.PersonService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestionPersonIntegrationTest {
	private static MySQLPersonDAO dao;
	private static PersonService service;
	private static Person testPerson;

	// Database variables
	private static final String URL = "jdbc:mysql://localhost:3306/gestion_person";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	@BeforeAll
	static void setUpBeforeAll() {
		try {
			Class.forName(DRIVER);
			dao = new MySQLPersonDAO();
			assertNotNull(dao);
			service = new PersonService(dao);
			assertNotNull(service);
			System.out.println("Driver found!");
		} catch (ClassNotFoundException ex) {
			fail("Driver not found, " + ex.getMessage());
		}
	}

	@BeforeEach
	void getConnectionTest(TestInfo info) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			assertNotNull(con);
			assertFalse(con.isClosed());
			System.out.println(info.getDisplayName());
		} catch (SQLException ex) {
			fail("Failed to open database connection, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Save Person using PersonService and MySQLPersonDAO")
	@Order(1)
	void savePersonTest() {
		try {
			Person fakePerson = new Person();
			// Check valid Person input
			assertThrows(IllegalArgumentException.class, () -> fakePerson.setFirstName(null));
			assertThrows(IllegalArgumentException.class, () -> fakePerson.setLastName(null));
			assertThrows(IllegalArgumentException.class, () -> fakePerson.setAge(-1));
			assertThrows(IllegalArgumentException.class, () -> fakePerson.setEmail(null));
			assertThrows(IllegalArgumentException.class, () -> fakePerson.setPassword(null));

			assertThrows(IllegalArgumentException.class, () -> fakePerson.setEmail("bademail_notNull"));

			Person person = new Person("Eveline", "Neema", 32, "neema@hotmail.com", "P@ssword");
			service.create(person);

			assertTrue(person.getId() > 0);
			testPerson = person;
		} catch (SQLException ex) {
			fail("Failed to save Person, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Update Person using PersonService and MySQLPersonDAO")
	@Order(2)
	void updatePersonTest() {
		try {
			testPerson.setFirstName("Alex");
			testPerson.setLastName("Ambrozio");
			testPerson.setAge(22);
			testPerson.setEmail("ambrozio@android.com");
			testPerson.setPassword("MyPass");
			service.update(testPerson);
			Person personUpdated = service.listPersonById(testPerson.getId());

			assertEquals(personUpdated.getFirstName(), testPerson.getFirstName());
			assertEquals(personUpdated.getLastName(), testPerson.getLastName());
			assertEquals(personUpdated.getAge(), testPerson.getAge());
			assertEquals(personUpdated.getEmail(), testPerson.getEmail());
		} catch (SQLException ex) {
			fail("Failed to update Person, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find Person by ID using PersonService and MySQLPersonDAO")
	@Order(3)
	void findPersonByIdTest() {
		try {
			Person person = service.listPersonById(testPerson.getId());
			assertEquals(person.getId(), testPerson.getId());
		} catch (SQLException ex) {
			fail("Failed to find Person by ID, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find Person by name using PersonService and MySQLPersonDAO")
	@Order(4)
	void findPersonByNameTest() {
		try {
			Person person = service.listPersonByName(testPerson.getFirstName());
			assertEquals(person.getFirstName(), testPerson.getFirstName());
		} catch (SQLException ex) {
			fail("Failed to find Person by name, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Find all Persons using PersonService and MySQLPersonDAO")
	@Order(5)
	void findPersonsTest() {
		try {
			List<Person> persons = new ArrayList<>();
			persons = service.listPersons();
			assertFalse(persons.isEmpty());
		} catch (SQLException ex) {
			fail("Failed to find Person by name, " + ex.getMessage());
		}
	}

	@Test
	@DisplayName("Delete Person using PersonService and MySQLPersonDAO")
	@Order(6)
	void deletePersonTest() {
		try {
			service.remove(testPerson);
			Person deletedPerson = service.listPersonById(testPerson.getId());
			assertNull(deletedPerson);
		} catch (SQLException ex) {
			fail("Failed to delete Person, " + ex.getMessage());
		} 
	}

	@Test
	@DisplayName("Delete inexisting Person using PersonService and MySQLPersonDAO")
	@Order(7)
	void deleteFakePersonTest() {
		try {
			Person fakePerson = new Person(0, "Fake_firstname", "Fake_lastname", 200, "fakeemail@fake.com",
					"fake_password");

			assertThrows(IllegalArgumentException.class, () -> service.remove(fakePerson));
		} catch (IllegalArgumentException ex) {
			fail("Failed to update Person, " + ex.getMessage());
		}
	}
}
