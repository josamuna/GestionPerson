package com.josamuna.project.application;

import java.sql.SQLException;

import com.josamuna.project.accessdata.MySQLPersonDAO;
import com.josamuna.project.model.Person;
import com.josamuna.project.model.PersonDAO;
import com.josamuna.project.service.PersonService;

public class MainPerson {

	public static void main(String[] args) {

		try {
			// Get Database access
			PersonDAO dao = new MySQLPersonDAO();
			
			// Get Service access
			PersonService service = new PersonService(dao);
			
			// Add new Persons
			Person p1 = new Person("Jos", "Isa", 15, "jos@jos.com", "pwd");
			service.create(p1);

			Person p2 = new Person("Sarah", "Mboneza", 15, "sarah@mobile.com", "P@ssword");
			service.create(p2);

			Person p3 = new Person("Ali", "Alia", 20, "alfa@gmail.com", "pwd");
			service.create(p3);

			Person p4 = new Person("Bad firstname", "Bad lastname", 42, "baduser@user.com", "badpassword");
			service.create(p4);

			// Show Person's info
			service.listPersons().forEach(person -> {
				System.out.println(person.toString());
			});

			// Update Person's info
			p1.setFirstName("Josue");
			p1.setLastName("Isamuna");
			p1.setAge(23);
			p1.setEmail("josue@iframe.com");
			p1.setPassword("josue@iframe.com");

			service.update(p1);

			p3.setFirstName("Alice");
			p3.setLastName("Alimasi");
			p3.setAge(42);
			p3.setEmail("alice@primus.com");
			p3.setPassword("P@ssword");

			service.update(p3);
			
			// Show Person's info
			service.listPersons().forEach(person -> {
				System.out.println(person.toString());
			});

			// Delete Person
			service.remove(p4);
			
			// Show Person's info
			service.listPersons().forEach(person -> {
				System.out.println(person.toString());
			});

			// Search Person by ID 2
			Person p5 = service.listPersonById(p2.getId());
			System.out.println(p5.toString());

			// Search Person by firstName Sarah
			Person p6 = service.listPersonByName(p2.getFirstName());
			System.out.println(p6.toString());
			
			// Try to delete invalid Person with ID doesn't exist
			Person p7 = new Person(0, "Invalid firstname", "Invalid lastname", 0, "invalidemail@invalid.com","InvalidPassword");
			service.remove(p7);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error occured when handling database, " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.err.println("Error occured when handling data, " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error occured, " + e.getMessage());
		}

	}

}
