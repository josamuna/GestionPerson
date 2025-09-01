package com.josamuna.project.accessdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.josamuna.project.model.Person;
import com.josamuna.project.model.PersonDAO;

public class MySQLPersonDAO implements PersonDAO {

	private final String url = "jdbc:mysql://localhost:3306/gestion_person";// ?user=root&password=''&serverTimezone=UTC
	private final String user = "root";
	private final String password = "";

	public MySQLPersonDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ec) {
			throw new RuntimeException("Driver not found!");
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public void save(Person person) {
		String sql = "INSERT INTO person(firstname,lastname,age,email,password) VALUES(?,?,?,?,?)";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setInt(3, person.getAge());
			ps.setString(4, person.getEmail());
			ps.setString(5, person.getPassword());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				person.setId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to save person!");
		}
	}

	@Override
	public void update(Person person) {
		String sql = "UPDATE person SET firstname=?,lastname=?,age=?,email=?,password=? WHERE id=?";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setInt(3, person.getAge());
			ps.setString(4, person.getEmail());
			ps.setInt(5, person.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update person!");
		}

	}

	@Override
	public void delete(Person person) {
		String sql = "DELETE FROM person WHERE id=?";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, person.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to delete person!");
		}

	}

	@Override
	public Person findPersonById(int id) {
		String sql = "SELECT id, firstname, lastname, age, email, password FROM person WHERE id=?";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Person(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getInt("age"), rs.getString("email"), rs.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get person's information data!");
		}

		return null;
	}

	@Override
	public Person findPersonByName(String firstName) {
		String sql = "SELECT id, firstname, lastname, age, email, password FROM person WHERE firstname like ?";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, firstName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Person(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getInt("age"), rs.getString("email"), rs.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get person's information data!");
		}
		return null;
	}

	@Override
	public List<Person> findPersons() {
		String sql = "SELECT id, firstname, lastname, age, email, password FROM person";
		List<Person> persons = new ArrayList<>();

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getInt("age"), rs.getString("email"), rs.getString("password"));
				persons.add(person);
			}

			return persons;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get all persons's information data!");
		}
	}

}
