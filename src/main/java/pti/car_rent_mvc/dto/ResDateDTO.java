package pti.car_rent_mvc.dto;

import java.time.LocalDate;

public class ResDateDTO {

	private LocalDate startDate;
	private LocalDate endDate;

	public ResDateDTO() {
		super();
	}

	public ResDateDTO(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
