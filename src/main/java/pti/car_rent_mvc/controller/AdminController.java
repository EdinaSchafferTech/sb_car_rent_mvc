package pti.car_rent_mvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import pti.car_rent_mvc.service.ReservationService;

@Controller
public class AdminController {

	private final ReservationService reservationService;

	public AdminController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@Value("${app.api-key1}")
	private String apiKey1;

	@Value("${app.api-key2}")
	private String apiKey2;

	private boolean isValidApiKey(String apiKey) {
		return apiKey != null && (apiKey.equals(apiKey1) || apiKey.equals(apiKey2));
	}

	@GetMapping("/admin")
	public String adminLogin(@RequestParam(required = false) String apiKey) {

		if (!isValidApiKey(apiKey)) {
			return "index";
		}

		return "admin";
	}
}