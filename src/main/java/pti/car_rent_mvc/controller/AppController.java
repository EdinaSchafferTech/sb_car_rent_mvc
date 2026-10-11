package pti.car_rent_mvc.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.car_rent_mvc.dto.FreeCarsDTO;
import pti.car_rent_mvc.dto.ResDateDTO;
import pti.car_rent_mvc.service.CarService;

@Controller
public class AppController {

	private final CarService carService;

	public AppController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/cars")
	public String getAvailableCars(@RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate, Model model) {

		ResDateDTO resDateDTO = new ResDateDTO(startDate, endDate);

		FreeCarsDTO freeCarsDTO = carService.getAvailableCars(resDateDTO);

		model.addAttribute("freeCarsDTO", freeCarsDTO);

		return "index";
	}

}
