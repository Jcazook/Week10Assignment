import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DaysToWorkdao;
import dao.Employeedao;
import entity.DaysToWork;
import entity.Employee;

public class Menu {
	
	public List<String> options = Arrays.asList("Show Schedule", "Show Employees", "Add Shifts",
	"Delete Shifts", "Add Employees", "Delete Employees", "Update Employee Info", "Update Shifts");
	
	private Employeedao employeesdao;
	private DaysToWorkdao daystoworkdao;
	public Scanner scanner = new Scanner(System.in);
		
	public void loadUp() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
	try {
		if(selection.equals("1")) {
		showSchedule();
	} else if (selection.equals("2")) {
		showEmployees();
	} else if (selection.equals("3")) {
		addShifts();
	} else if (selection.equals("4")) {
		deleteShifts();
	} else if (selection.equals("5")) {
		addEmployees();
	} else if (selection.equals("6")) {
		deleteEmployees();
	} else if (selection.equals("7")) {
		updateEmployees();
	} else if (selection.equals("8")) {
		updateScheduleWeek();
	}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Press any option to continue");
		scanner.nextLine();
		} while (!selection.equals("-1"));
	}
	
	public void printMenu() {
		System.out.println("Select Any Option Below:");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ". " + options.get(i));
		}
	}

	private void showSchedule() throws SQLException {
 		List<DaysToWork> daystowork = daystoworkdao.getDaysToWork();
 		for (DaysToWork days : daystowork)
 		System.out.println(days.getEmployeeId() + ": " + days.getMonday() + days.getTuesday() + days.getWednesday() + days.getThursday()
 			+ days.getFriday() + days.getSaturday() + days.getSunday()); //I think I have to add the rest of the days, but its too late for that
 	}
	
	//Problem with this one, try to find out and move on for now
	public void showEmployees() throws SQLException {
		System.out.println("Enter employee ID");
	//	int id = Integer.parseInt(scanner.nextLine());
	//	Employee employee = Employeedao.getEmployeesByEmployeeId();
	//	System.out.println(employee.getEmployeeId() + ": " + employee.getFirstName() + " " + employee.getLastName());
	}
	public void addEmployees() throws SQLException {
		System.out.println("Enter the new employees first name");
		String firstName = scanner.nextLine();
		System.out.println("Enter the new employees last name");
		String lastName = scanner.nextLine();
		Employeedao employeedao = new Employeedao();
		employeedao.addNewEmployee(firstName, lastName);
	}
	
	private void deleteEmployees () throws SQLException {
		System.out.println("Enter employee id to delete");
		int id = Integer.parseInt(scanner.nextLine());
		employeesdao.deleteEmployeeById(id);
	}
	public void addShifts () throws SQLException{
		System.out.println("What is the employee id?");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Will the employee be working on Monday?");
		String monday = scanner.nextLine();
		System.out.println("Will the employee be working on Tuesday");
		String tuesday = scanner.nextLine();
		System.out.println("Will the employee be working on Wednesday");
		String wednesday = scanner.nextLine();
		System.out.println("Will the employee be working on Thursday");
		String thursday = scanner.nextLine();
		System.out.println("Will the employee be working on Friday");
		String friday = scanner.nextLine();
		System.out.println("Will the employee be working on Saturday");
		String saturday = scanner.nextLine();
		System.out.println("Will the employee be working on Sunday");
		String sunday = scanner.nextLine();
		DaysToWorkdao daystoworkdao = new DaysToWorkdao();
		daystoworkdao.addNewEmployee(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
	}
	
	private void deleteShifts () throws SQLException {
		System.out.println("Enter employee id to delete");
		int id = Integer.parseInt(scanner.nextLine());
		employeesdao.deleteShiftByEmployeeId(id);
	}
	
	private void updateEmployees() throws SQLException {
		System.out.println("Enter the id of the employee whose info you want to change");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("What will the new first name be?");
		String firstName = scanner.nextLine();
		System.out.println("Will will the new last name be?");
		String lastName = scanner.nextLine();
		employeesdao.updateEmployeesInfo(id, firstName, lastName);
	}
	
	private void updateScheduleWeek() throws SQLException{
		System.out.println("Enter the id of the employee whose info you want to change");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Will the employee now be working on monday?");
		String monday = scanner.nextLine();
		System.out.println("Will the employee now be working on tuesday?");
		String tuesday = scanner.nextLine();
		System.out.println("Will the employee now be working on wednesday?");
		String wednesday = scanner.nextLine();
		System.out.println("Will the employee now be working on thursday?");
		String thursday = scanner.nextLine();
		System.out.println("Will the employee now be working on friday?");
		String friday = scanner.nextLine();
		System.out.println("Will the employee now be working on saturday?");
		String saturday = scanner.nextLine();
		System.out.println("Will the employee now be working on sunday?");
		String sunday = scanner.nextLine();
		
		daystoworkdao.updateSchedule(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
	}
	
}