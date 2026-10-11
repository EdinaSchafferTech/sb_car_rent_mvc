package pti.car_rent_mvc.dto;

public class SimpleResponseDTO {

	private String response;

	public SimpleResponseDTO() {
		super();
	}

	public SimpleResponseDTO(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
