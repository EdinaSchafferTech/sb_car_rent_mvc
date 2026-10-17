package pti.car_rent_mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.car_rent_mvc.dto.AdminPageDTO;
import pti.car_rent_mvc.dto.CarDTO;
import pti.car_rent_mvc.dto.ResDateDTO;
import pti.car_rent_mvc.dto.ReservationDTO;
import pti.car_rent_mvc.dto.UserResDTO;
import pti.car_rent_mvc.dto.UserSelectionDTO;
import pti.car_rent_mvc.model.Car;
import pti.car_rent_mvc.model.Reservation;
import pti.car_rent_mvc.service.CarService;
import pti.car_rent_mvc.service.ReservationService;

@Controller
public class AdminController {

	private final ReservationService reservationService;
	private final CarService carService;

	@Value("${app.api-key1}")
	private String apiKey1;

	@Value("${app.api-key2}")
	private String apiKey2;

	public AdminController(ReservationService reservationService, CarService carService) {
		super();
		this.reservationService = reservationService;
		this.carService = carService;
	}

	private boolean isValidApiKey(String apiKey) {
		return apiKey != null && (apiKey.equals(apiKey1) || apiKey.equals(apiKey2));
	}

	@GetMapping("/admin/cars/{id}/edit")
	public String editCar(@RequestParam String apiKey, @PathVariable int id, Model model) {

		if (!isValidApiKey(apiKey)) {
			return "index";
		}

		Car car = carService.getCarById(id);

		if (car == null) {
			return "index";
		}

		CarDTO carDTO = new CarDTO();

		carDTO.setId(car.getId());
		carDTO.setType(car.getType());
		carDTO.setPrice(car.getDailyPrice());
		carDTO.setActive(car.isActive());

		model.addAttribute("selectedCar", carDTO);
		model.addAttribute("apiKey", apiKey);

		return "adminEditCar";

	}

	@PostMapping("/admin/car/edit/finish")
	public String finishEditCar(@RequestParam String apiKey, CarDTO carDTO, Model model) {

		if (!isValidApiKey(apiKey)) {
			return "index";
		}

		Car car = carService.getCarById(carDTO.getId());

		if (car == null) {
			System.out.println("Car is not found, ID: " + carDTO.getId());
			return "index";
		}

		System.out.println("Car, ID: " + car.getId());

		car.setType(carDTO.getType());
		car.setDailyPrice(carDTO.getPrice());
		car.setActive(carDTO.isActive());

		carService.saveCar(car);

		AdminPageDTO adminPageDTO = new AdminPageDTO();
		adminPageDTO.setAllReservation(reservationService.getAllReservationsDTO());
		adminPageDTO.setAllCar(carService.getAllCarsDTO());
		adminPageDTO.setResponse("Successfully saved");

		model.addAttribute("adminPageDTO", adminPageDTO);

		return "admin";
	}

	@PostMapping("/admin/cars/{id}/deactivate")
	public String deactivateCar(@RequestParam String apiKey, @PathVariable int id, Model model) {

		if (!isValidApiKey(apiKey)) {
			return "index";
		}

		Car car = carService.getCarById(id);

		if (car == null) {
			return "index";
		}

		car.setActive(false);
		carService.saveCar(car);

		AdminPageDTO adminPageDTO = new AdminPageDTO();
		adminPageDTO.setAllReservation(reservationService.getAllReservationsDTO());
		adminPageDTO.setAllCar(carService.getAllCarsDTO());
		adminPageDTO.setResponse("Car successfully deactivated");

		model.addAttribute("adminPageDTO", adminPageDTO);

		return "admin";
	}

	@GetMapping("/")
	public String adminLogin(@RequestParam(required = false) String apiKey, Model model) {

		if (!isValidApiKey(apiKey)) {
			return "index";
		}

		AdminPageDTO adminPageDTO = new AdminPageDTO();

		adminPageDTO.setAllReservation(reservationService.getAllReservationsDTO());
		adminPageDTO.setAllCar(carService.getAllCarsDTO());
		adminPageDTO.setResponse("");

		model.addAttribute("adminPageDTO", adminPageDTO);

		return "admin";
	}
}