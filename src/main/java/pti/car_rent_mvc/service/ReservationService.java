package pti.car_rent_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pti.car_rent_mvc.dto.ResDateDTO;
import pti.car_rent_mvc.dto.ReservationDTO;
import pti.car_rent_mvc.dto.UserResDTO;
import pti.car_rent_mvc.dto.UserSelectionDTO;
import pti.car_rent_mvc.model.Car;
import pti.car_rent_mvc.model.Reservation;
import pti.car_rent_mvc.repository.CarRepository;
import pti.car_rent_mvc.repository.ReservationRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final CarRepository carRepository;

	public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.carRepository = carRepository;
	}

	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = new ArrayList<>();

		for (Reservation reservation : reservationRepository.findAll()) {
			reservations.add(reservation);
		}

		return reservations;
	}

	public List<ReservationDTO> getAllReservationsDTO() {

		List<ReservationDTO> reservationDTOList = new ArrayList<>();

		for (Reservation reservation : getAllReservations()) {

			ResDateDTO resDateDTO = new ResDateDTO(reservation.getStartDate(), reservation.getEndDate());

			UserSelectionDTO userSelectionDTO = new UserSelectionDTO(reservation.getCarId(), resDateDTO);

			UserResDTO userResDTO = new UserResDTO(reservation.getName(), reservation.getEmail(),
					reservation.getAddress(), reservation.getPhone(), userSelectionDTO);

			ReservationDTO reservationDTO = new ReservationDTO(reservation.getId(), userResDTO, reservation.getPrice());

			reservationDTOList.add(reservationDTO);
		}

		return reservationDTOList;
	}

	public Reservation createReservation(Reservation reservation) {

		if (!reservation.getStartDate().isBefore(reservation.getEndDate())) {
			throw new IllegalArgumentException("Start date must be earlier than the end date");
		}
		int conflicts = reservationRepository.countOverlappingReservations(reservation.getCarId(),
				reservation.getStartDate(), reservation.getEndDate());

		if (conflicts > 0) {
			throw new IllegalArgumentException("Car is not available in this term");
		}

		Car car = null;

		for (Car currentCar : carRepository.findAll()) {
			if (currentCar.getId() == reservation.getCarId()) {
				car = currentCar;
			}
		}
		if (car == null) {
			throw new IllegalArgumentException("Car is not found");
		}
		if (!car.isActive()) {
			throw new IllegalArgumentException("This car is not available in this term");
		}

		int days = (int) java.time.temporal.ChronoUnit.DAYS.between(reservation.getStartDate(),
				reservation.getEndDate());

		int totalPrice = days * car.getDailyPrice();
		reservation.setPrice(totalPrice);

		return reservationRepository.save(reservation);

	}
}
