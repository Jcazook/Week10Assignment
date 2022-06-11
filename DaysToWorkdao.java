package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import entity.DaysToWork;

public class DaysToWorkdao {
	
	private Connection connection;
	private Employeedao employeedao;
	private final String GET_DAYSTOWORK_QUERY = "Select * from daystowork";
	private final String GET_SCHEDULE_BY_ID_QUERY = "Select * from daystowork where employees_id = ?";
	private final String ADD_NEW_SHIFT_BY_EMPLOYEE_ID_QUERY = "insert into daystowork (employees_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE_SHIFT_BY_EMPLOYEE_ID_QUERY = "update daystowork set monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ? where id = 1;";
	
	public DaysToWorkdao() {
		connection = DBJavaConnection.getConnection();
	}
	
	public List<DaysToWork> getDaysToWork() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_DAYSTOWORK_QUERY).executeQuery();
		List<DaysToWork> workDays = new ArrayList<DaysToWork>();
		
		while (rs.next()) {
			workDays.add(scheduleDays(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
			rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
		}
		
		return workDays;
	}
	public DaysToWork scheduleId(int id) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(GET_SCHEDULE_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return scheduleDays (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
				rs.getString(6), rs.getString(7), rs.getString(8));
	}
	
	private DaysToWork scheduleDays(int id, String monday, String tuesday, String wednesday, 
			String thursday, String friday, String saturday, String sunday) throws SQLException {
		return new DaysToWork(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday,
		employeedao.getEmployeesByEmployeeId(id));
	}
	
	public void addNewEmployee (int id, String monday, String tuesday, String wednesday, String thursday, 
			String friday, String saturday, String sunday) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_SHIFT_BY_EMPLOYEE_ID_QUERY);
		ps.setInt(1, 1);
		ps.setString(2, monday);
		ps.setString(3, tuesday);
		ps.setString(4, wednesday);
		ps.setString(5, thursday);
		ps.setString(6, friday);
		ps.setString(7, saturday);
		ps.setString(8, sunday);
		ps.executeQuery();
	}

	public void updateSchedule(int id, String monday, String tuesday, String wednesday, String thursday, String friday,
			String saturday, String sunday) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(UPDATE_SHIFT_BY_EMPLOYEE_ID_QUERY);	
		ps.setInt(1, id);
		ps.setString(2, monday);
		ps.setString(3, tuesday);
		ps.setString(4, wednesday);
		ps.setString(5, thursday);
		ps.setString(6, friday);
		ps.setString(7, saturday);
		ps.setString(8, sunday);
		ps.executeQuery();
	}
	
}

