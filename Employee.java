package entity;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	
	public Employee(int employeeId, String firstName, String lastName) {
		this.setEmployeeId(employeeId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
