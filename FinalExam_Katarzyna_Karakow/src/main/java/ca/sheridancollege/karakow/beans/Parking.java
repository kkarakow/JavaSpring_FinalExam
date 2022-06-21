package ca.sheridancollege.karakow.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking implements Serializable{

	private int ticketNo;
	private int spotNo;
	@NonNull
	private String plateNo;
	private String date;
	
	
}
