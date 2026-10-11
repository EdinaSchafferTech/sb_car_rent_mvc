package pti.car_rent_mvc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import pti.car_rent_mvc.model.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {

	@Query("SELECT * FROM car WHERE active=true")
	List<Car> findAllActiveCars();

}
