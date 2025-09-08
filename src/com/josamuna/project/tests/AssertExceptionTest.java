package com.josamuna.project.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class AssertExceptionTest {
	@Test
	void testAssertThrows() {
		String error1 = "Failed to connect to the Database!";
		String error2 = "Failed to handle data!";

		SQLException sqlEx = assertThrows(SQLException.class, () -> {
			throw new SQLException(error1);
		});

		assertEquals(error1, sqlEx.getMessage()); // Pass.

		Exception ex = assertThrows(Exception.class, () -> {
			throw new SQLException(error2);
		});

		assertEquals(error2, ex.getMessage()); // Pass. Exception est une super classe de IllegalArgumentException
	}

	@Test
	public void testAssertThrowsExactly() {
		String error1 = "Division by zero is not allowed!";
		String error2 = "Invalid number passed";

		ArithmeticException arEx = assertThrowsExactly(ArithmeticException.class, () -> {
			throw new ArithmeticException(error1);
		});

		assertEquals(error1, arEx.getMessage()); // Pass

		Exception ex = assertThrowsExactly(Exception.class, () -> {
			throw new ArithmeticException(error2);
		});

		assertEquals(error2, ex.getMessage()); // Failed. Exception n'est pas la classe ArithmeticException
	}

	int divide(int x, int y) throws ArithmeticException {
		return x / y;
	}

	@Test
	void testDivideByZero() {
		int x = 10;
		int y = 0;
		ArithmeticException arEx = assertThrows(ArithmeticException.class, () -> divide(x, y));
		assertTrue(arEx.getMessage().toLowerCase().contains("/ by zero"), arEx.getMessage());
	}

}
