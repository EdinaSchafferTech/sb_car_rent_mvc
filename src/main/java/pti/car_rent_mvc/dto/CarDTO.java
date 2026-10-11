package pti.car_rent_mvc.dto;

public class CarDTO {

	private int id;
	private String type;
	private int price;
	private boolean active;
	private String picture;

	public CarDTO() {
		super();
	}

	public CarDTO(int id, String type, int price, boolean active, String picture) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.active = active;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
