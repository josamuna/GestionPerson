package com.josamuna.project.application;

import com.josamuna.project.accessdata.MySQLPersonDAO;
import com.josamuna.project.model.Person;
import com.josamuna.project.model.PersonDAO;
import com.josamuna.project.service.PersonService;

public class MainPerson {

	public static void main(String[] args) {
		// Get Database access
		PersonDAO dao = new MySQLPersonDAO();
		
		// Get Service access
		PersonService service = new PersonService(dao);
		
		// Add new Persons
		Person p1 = new Person("Josue", "Isamuna",23, "josue@iframe.com","P@ssword");
		service.create(p1);
		
		Person p2 = new Person("Sarah", "Mboneza",15, "sarah@mobile.com","P@ssword");
		service.create(p2);
		
		Person p3 = new Person("Alice", "Alimasi",42, "alice@primus.com","P@ssword");
		service.create(p3);
		
		service.listPersons();
		
	}

}
