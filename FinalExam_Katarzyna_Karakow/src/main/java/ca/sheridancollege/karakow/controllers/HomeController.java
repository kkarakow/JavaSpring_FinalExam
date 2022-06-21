package ca.sheridancollege.karakow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.karakow.beans.Parking;
import ca.sheridancollege.karakow.beans.Spot;
import ca.sheridancollege.karakow.database.DatabaseAccess;

@Controller
public class HomeController {

	@Autowired
	DatabaseAccess da;
	
	@GetMapping("/")
	public String goHome(Model model) {
		
		model.addAttribute("parking", new Parking());
		model.addAttribute("parkingList", da.getParking());
		model.addAttribute("spot", new Spot());
		model.addAttribute("spotList", da.getSpot());
		
		return "index";
	}
	
	@PostMapping("/processForm")
	public String insert(Model model,
			@RequestParam int spotNo,
			@RequestParam String plateNo,
			@RequestParam String date,
			@ModelAttribute Parking parking) {
		
		da.insertParkingDetails(spotNo, plateNo, date);
		
		model.addAttribute("parking", new Parking());
		model.addAttribute("parkingList", da.getParking());
		model.addAttribute("spotList", da.getSpot());
		
		return "index";
	}

}
