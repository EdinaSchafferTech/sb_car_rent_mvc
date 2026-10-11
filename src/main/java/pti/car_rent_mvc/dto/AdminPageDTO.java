package pti.car_rent_mvc.dto;

import java.util.List;

public class AdminPageDTO {

	private List<ReservationDTO> allReservation;
	private List<CarDTO> allCar;
	private String response;

	public AdminPageDTO() {
		super();
	}

	public AdminPageDTO(List<ReservationDTO> allReservation, List<CarDTO> allCar, String response) {
		super();
		this.allReservation = allReservation;
		this.allCar = allCar;
		this.response = response;
	}

	public List<ReservationDTO> getAllReservation() {
		return allReservation;
	}

	public void setAllReservation(List<ReservationDTO> allReservation) {
		this.allReservation = allReservation;
	}

	public List<CarDTO> getAllCar() {
		return allCar;
	}

	public void setAllCar(List<CarDTO> allCar) {
		this.allCar = allCar;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
