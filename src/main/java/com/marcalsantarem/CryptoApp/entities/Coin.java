package com.marcalsantarem.CryptoApp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Coin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private BigDecimal price;
	private BigDecimal quantity;
	private Timestamp dateTime;
	
	public Coin() {}
	
	public Coin(int id, String name, BigDecimal price, BigDecimal quantity, Timestamp dateTime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		return id == other.id;
	}

}