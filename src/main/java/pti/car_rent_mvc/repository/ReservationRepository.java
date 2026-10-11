package pti.car_rent_mvc.repository;

import java.time.LocalDate;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pti.car_rent_mvc.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	
	@Query("SELECT COUNT(*) FROM reservation WHERE car_id = :carId AND start_date <= :endDate AND end_date >= :startDate")
	int countOverlappingReservations(
		    @Param("carId") int carId,
		    @Param("startDate") LocalDate startDate,
		    @Param("endDate") LocalDate endDate
		);

}
