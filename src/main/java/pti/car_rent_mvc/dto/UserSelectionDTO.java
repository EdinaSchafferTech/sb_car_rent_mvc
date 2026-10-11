package pti.car_rent_mvc.dto;

public class UserSelectionDTO {

	private int carId;
	private ResDateDTO resDateDTO;

	public UserSelectionDTO() {
		super();
	}

	public UserSelectionDTO(int carId, ResDateDTO resDateDTO) {
		super();
		this.carId = carId;
		this.resDateDTO = resDateDTO;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public ResDateDTO getResDateDTO() {
		return resDateDTO;
	}

	public void setResDateDTO(ResDateDTO resDateDTO) {
		this.resDateDTO = resDateDTO;
	}

}
