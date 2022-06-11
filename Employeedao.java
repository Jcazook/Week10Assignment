package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;

public class Employeedao {

	private Connection connection;
	private final String GET_EMPLOYEES_BY_EMPLOYEE_ID_QUERY = "Select * from employees where id = ?";
	private final String ADD_NEW_EMPLOYEE_QUERY = "Insert into employees (first_name, last_name) values (?, ?)";
	private final String DELETE_EMPLOYEE_BY_ID = "Delete from employees where id = ?";
	private final String DELETE_SHIFT_BY_EMPLOYEE_ID = "Delete from daystowork where employees_id = ?";
	private final String UPDATE_EMPLOYEE_INFO_BY_ID = "update employees set first_name = ?, last_name = ? where id = ?";

	public Employeedao() {
	connection = DBJavaConnection.getConnection();
	}
	
	public List<Employee> getEmployeesByEmployeeId(int empNo) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEES_BY_EMPLOYEE_ID_QUERY);
		ps.setInt(1, empNo);
		ResultSet rs = ps.executeQuery();
		List<Employee> employees = new ArrayList<Employee>();
		
		while (rs.next()) {
			employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
	return employees;
	}  
	
	public void addNewEmployee (String firstName, String lastName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_EMPLOYEE_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
	}

	public void deleteEmployeeById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void deleteShiftByEmployeeId(int id) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(DELETE_SHIFT_BY_EMPLOYEE_ID);	
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void updateEmployeesInfo(int id, String firstName, String lastName) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_INFO_BY_ID);	
		ps.setInt(1, id);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ps.executeQuery();
	}
	
}
