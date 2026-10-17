package pti.car_rent_mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pti.car_rent_mvc.dto.CarDTO;
import pti.car_rent_mvc.dto.FreeCarsDTO;
import pti.car_rent_mvc.dto.ResDateDTO;
import pti.car_rent_mvc.model.Car;
import pti.car_rent_mvc.repository.CarRepository;
import pti.car_rent_mvc.repository.ReservationRepository;

@Service
public class CarService {

	private final CarRepository carRepository;
	private final ReservationRepository reservationRepository;

	public CarService(CarRepository carRepository, ReservationRepository reservationRepository) {
		super();
		this.carRepository = carRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<>();

		for (Car car : carRepository.findAll()) {
			cars.add(car);
		}

		return cars;
	}

	public List<CarDTO> getAllCarsDTO() {

		List<CarDTO> carDTOList = new ArrayList<>();

		for (Car car : getAllCars()) {

			CarDTO carDTO = new CarDTO();

			carDTO.setId(car.getId());
			carDTO.setType(car.getType());
			carDTO.setPrice(car.getDailyPrice());
			carDTO.setActive(car.isActive());

			carDTOList.add(carDTO);
		}

		return carDTOList;
	}

	public List<Car> getActiveCars() {
		return carRepository.findActiveCars();
	}

	public FreeCarsDTO getAvailableCars(ResDateDTO resDateDTO) {
		LocalDate startDate = resDateDTO.getStartDate();
		LocalDate endDate = resDateDTO.getEndDate();

		List<Car> activeCars = getActiveCars();
		List<CarDTO> freeCars = new ArrayList<>();

		for (Car car : activeCars) {
			int conflicts = reservationRepository.countOverlappingReservations(car.getId(), startDate, endDate);

			if (conflicts == 0) {

				CarDTO carDTO = new CarDTO();

				carDTO.setId(car.getId());
				carDTO.setType(car.getType());
				carDTO.setPrice(car.getDailyPrice());
				carDTO.setActive(car.isActive());

				freeCars.add(carDTO);
			}
		}

		FreeCarsDTO freeCarsDTO = new FreeCarsDTO();
		freeCarsDTO.setFreeCars(freeCars);
		freeCarsDTO.setResDateDTO(resDateDTO);

		return freeCarsDTO;
	}

	public Car saveCar(Car car) {
		return carRepository.save(car);

	}

	public Car getCarById(int id) {
		for (Car car : getAllCars()) {
			if (car.getId() == id) {
				return car;
			}
		}
		return null;
	}

}
