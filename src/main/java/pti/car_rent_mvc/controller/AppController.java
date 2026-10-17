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
import pti.car_rent_mvc.dto.UserResDTO;
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

	@PostMapping("/cars")
	public String getAvailableCars(ResDateDTO resDateDTO, Model model) {

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
	public String finishReservation(UserResDTO userResDTO, Model model) {

		Reservation reservation = new Reservation();

		reservation.setName(userResDTO.getName());
		reservation.setEmail(userResDTO.getEmail());
		reservation.setAddress(userResDTO.getAddress());
		reservation.setPhone(userResDTO.getPhone());

		UserSelectionDTO userSelectionDTO = userResDTO.getUserSelectionDTO();

		reservation.setCarId(userSelectionDTO.getCarId());
		reservation.setStartDate(userSelectionDTO.getResDateDTO().getStartDate());
		reservation.setEndDate(userSelectionDTO.getResDateDTO().getEndDate());

		Reservation savedReservation = reservationService.createReservation(reservation);

		model.addAttribute("totalPrice", savedReservation.getPrice());

		SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
		simpleResponseDTO.setResponse("Reservation successful!");

		model.addAttribute("simpleResponseDTO", simpleResponseDTO);

		return "result";

	}

}
