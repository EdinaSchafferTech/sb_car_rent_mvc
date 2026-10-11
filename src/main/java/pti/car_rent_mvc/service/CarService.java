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

	public List<Car> getActiveCars() {
		List<Car> allCars = getAllCars();
		List<Car> activeCars = new ArrayList<>();

		for (Car car : allCars) {
			if (car.isActive()) {
				activeCars.add(car);
			}
		}

		return activeCars;
	}
	public FreeCarsDTO getAvailableCars(ResDateDTO resDateDTO) {
        LocalDate startDate = resDateDTO.getStartDate();
        LocalDate endDate = resDateDTO.getEndDate();

        List<Car> activeCars = getActiveCars();
        List<CarDTO> freeCars = new ArrayList<>();

        for (Car car : activeCars) {
            int conflicts = reservationRepository.countOverlappingReservations(
                    car.getId(), startDate, endDate);

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


}
