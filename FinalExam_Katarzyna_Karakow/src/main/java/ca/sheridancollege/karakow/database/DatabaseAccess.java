package ca.sheridancollege.karakow.database;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.karakow.beans.Parking;
import ca.sheridancollege.karakow.beans.Spot;


@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertParkingDetails(int spotNo, String plateNo,
			String date) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO ParkingDetails(spotNo, plateNo, date)"
				+ "VALUES(:spotNo, :plateNo, :date)";
		String query2 = "UPDATE AvailableSpots SET spotsAvailable=false "
				+ "WHERE spotNo=:spotNo";
		
		
		namedParameters.addValue("spotNo", spotNo);
		namedParameters.addValue("plateNo", plateNo.toUpperCase());
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
	
}