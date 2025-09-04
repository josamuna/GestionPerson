package com.josamuna.project.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertionsTest {

	@Test
	void test() {
		String obj1 = "JUnit";
		String obj2 = "JUnit";
		String obj3 = "Testing";
		String obj4 = "Testing";
		String obj5 = obj3;
		String obj6 = null;
		Object obj7 = new Object();
		Object obj8 = new Object();
		int num = 12;
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 1, 2, 3 };
		int[] arr3 = arr1;

		assertEquals(obj1, obj2); // Pass. Même valeurs
		assertSame(obj3, obj5); // Pass. Références identiques
		assertSame(obj3, obj4); // Pass. Valeurs identiques
		assertNotSame(obj7, obj8); // Pass. Références différentes
		assertTrue(num > 10); // Pass.
		assertFalse(num - 6 == 3); // Pass. 12 - 6 = 9 Différent de 3
		assertNull(obj6); // Pass. Valeur nulle
		assertNotNull(num); // Pass. Nombre différent de null
		assertArrayEquals(arr1, arr2); // Pass. Valeurs idems
		assertArrayEquals(arr1, arr3); // Pass. Références idems
	}

}
