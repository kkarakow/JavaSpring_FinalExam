package ca.sheridancollege.karakow.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.karakow.beans.Parking;
import ca.sheridancollege.karakow.beans.Spot;
import ca.sheridancollege.karakow.beans.User;
import lombok.NonNull;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertParkingDetails(Integer spotNo, String plateNo,
			String date) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO ParkingDetails(spotNo, plateNo, date)"
				+ "VALUES(:spotNo, :plateNo, :date)";
		String query2 = "UPDATE AvailableSpots SET spotsAvailable=false"
				+ "WHERE spotNo=:spotNo";
		
		
		namedParameters.addValue("spotNo", spotNo);
		namedParameters.addValue("plateNo", plateNo);
		namedParameters.addValue("date", date);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if (rowsAffected > 0) {
			jdbc.update(query2, namedParameters);
			System.out.println("Inserted ParkingDetails into database");
		}
	}
	
		
		public List<Parking> getParking() {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String query = "SELECT * FROM ParkingDetails";
			
			return jdbc.query(query, namedParameters, 
					new BeanPropertyRowMapper<Parking>(Parking.class));
		}
		
		public List<Spot> getSpot() {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String query = "SELECT * FROM AvailableSpots";
			
			return jdbc.query(query, namedParameters, 
					new BeanPropertyRowMapper<Spot>(Spot.class));
		}
	
		public User findUserAccount(String email) {
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM sec_user where email=:email"; 
			
			parameters.addValue("email", email);
			
			ArrayList<User> users = 
					(ArrayList<User>)jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User. class));
			if (users.size()>0) 
				return users.get(0); 
			else return null;
		}
		
		public List<String> getRolesById(Long userId) { 
			
			ArrayList<String> roles = new ArrayList<String>(); 
			
			MapSqlParameterSource parameters = new MapSqlParameterSource(); 
			
			String query = "select user_role.userId, sec_role.roleName "
					+ "FROM user_role, sec_role "
					+ "WHERE user_role.roleId=sec_role.roleId " 
					+ "AND userId=:userId";
			
			parameters.addValue("userId", userId);
			
			List<Map<String, Object>> rows = jdbc.queryForList(query, parameters); 
			
			for (Map<String, Object> row :rows) {
				roles.add((String)row.get("roleName"));
			}
			return roles;
		}
		
	
	
	
	
	
	
	
	
}
