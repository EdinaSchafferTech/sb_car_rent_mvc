package pti.car_rent_mvc.dto;

public class ReservationDTO {

	private int id;
	private UserResDTO userResDTO;

	public ReservationDTO() {
		super();
	}

	public ReservationDTO(int id, UserResDTO userResDTO) {
		super();
		this.id = id;
		this.userResDTO = userResDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserResDTO getUserResDTO() {
		return userResDTO;
	}

	public void setUserResDTO(UserResDTO userResDTO) {
		this.userResDTO = userResDTO;
	}

}
