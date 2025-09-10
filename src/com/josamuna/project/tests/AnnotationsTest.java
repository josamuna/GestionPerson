package com.josamuna.project.tests;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnotationsTest {
	@BeforeEach
	void beforeEach() throws Exception {
		System.out.println("BeforeEach");
	}
	
	@BeforeAll
	static void beforeClass() throws Exception {
		System.out.println("BeforeAll");
	}
	
	@AfterEach
	void afterEach() throws Exception {
		System.out.println("AfterEach");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("AfterAll");
	}
	@Test
	void test1() {
		System.out.println("Test1");
	}
	
	@Test
	void test2() {
		System.out.println("Test2");
	}
	
	@Test
	void test3() {
		System.out.println("Test3");
	}
	
	@Ignore
	void test4() {
		System.out.println("Ignore");
	}
}
