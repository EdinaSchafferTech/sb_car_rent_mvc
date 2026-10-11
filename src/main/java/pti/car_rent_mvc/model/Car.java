package pti.car_rent_mvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("car")
public class Car {

	@Id
	@Column
	private int id;

	@Column("type")
	private String type;

	@Column("picture")
	private String picture;

	@Column("daily_price")
	private int dailyPrice;

	@Column
	private boolean active;

	public Car() {
		super();
	}

	public Car(int id, String type, String picture, int dailyPrice, boolean active) {
		super();
		this.id = id;
		this.type = type;
		this.picture = picture;
		this.dailyPrice = dailyPrice;
		this.active = active;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(int dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}