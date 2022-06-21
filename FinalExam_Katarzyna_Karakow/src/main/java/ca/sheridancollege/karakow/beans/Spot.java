package ca.sheridancollege.karakow.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Spot implements Serializable{

	private int spotNo;
	@NonNull
	private String spotLocation;
	private boolean spotsAvailable;
	
}
