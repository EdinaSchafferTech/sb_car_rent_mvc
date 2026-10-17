package pti.car_rent_mvc.dto;

public class ReservationDTO {

	private int id;
	private UserResDTO userResDTO;
	private int price;

	public ReservationDTO() {
		super();
	}
	public ReservationDTO(int id, UserResDTO userResDTO, int price) {
		super();
		this.id = id;
		this.userResDTO = userResDTO;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
}
