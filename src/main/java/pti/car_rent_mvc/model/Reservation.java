package pti.car_rent_mvc.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("reservation")
public class Reservation {

	@Id
	@Column
	private int id;

	@Column("car_id")
	private int carId;

	@Column("name")
	private String name;

	@Column("email")
	private String email;

	@Column("address")
	private String address;

	public Reservation(int id, int carId, String name, String email, String address, String phone, LocalDate startDate,
			LocalDate endDate, int price) {
		super();
		this.id = id;
		this.carId = carId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}

	@Column("phone")
	private String phone;

	@Column("start_date")
	private LocalDate startDate;

	@Column("end_date")
	private LocalDate endDate;

	@Column("price")
	private int price;

	public Reservation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
