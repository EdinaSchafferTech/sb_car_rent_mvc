package pti.car_rent_mvc.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewCarDTO {

	private String type;
	private int price;
	private boolean active;
	private MultipartFile picture;

	public NewCarDTO() {
		super();
	}

	public NewCarDTO(String type, int price, boolean active, MultipartFile picture) {
		super();
		this.type = type;
		this.price = price;
		this.active = active;
		this.picture = picture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

}
