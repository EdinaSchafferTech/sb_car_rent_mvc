package pti.car_rent_mvc.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.car_rent_mvc.dto.FreeCarsDTO;
import pti.car_rent_mvc.dto.ResDateDTO;
import pti.car_rent_mvc.dto.SimpleResponseDTO;
import pti.car_rent_mvc.dto.UserSelectionDTO;
import pti.car_rent_mvc.model.Reservation;
import pti.car_rent_mvc.service.CarService;
import pti.car_rent_mvc.service.ReservationService;

@Controller
public class AppController {

	private final CarService carService;
	private final ReservationService reservationService;

	public AppController(CarService carService, ReservationService reservationService) {
		super();
		this.carService = carService;
		this.reservationService = reservationService;
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

	@GetMapping("/cars/res/start")
	public String startReservation(@RequestParam("carId") int carId, @RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate, Model model) {

		ResDateDTO resDateDTO = new ResDateDTO(startDate, endDate);

		UserSelectionDTO userSelectionDTO = new UserSelectionDTO();
		userSelectionDTO.setCarId(carId);
		userSelectionDTO.setResDateDTO(resDateDTO);

		model.addAttribute("userSelectionDTO", userSelectionDTO);

		return "res";
	}

	@PostMapping("/cars/res/finish")
	public String finishReservation(@RequestParam("carId") int carId, @RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("phone") String phone, Model model) {

		Reservation reservation = new Reservation();

		reservation.setCarId(carId);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setName(name);
		reservation.setEmail(email);
		reservation.setAddress(address);
		reservation.setPhone(phone);

		reservationService.createReservation(reservation);

		SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
		simpleResponseDTO.setResponse("Reservation successful!");

		model.addAttribute("simpleResponseDTO", simpleResponseDTO);

		return "result";
	}

}
