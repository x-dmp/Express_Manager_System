package cn.edu.cuit.domain;

import java.io.Serializable;

public class Express implements Serializable{

	public Express () {
		super();
	}

	public Express(Integer id, String name, Double price, String introduce) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.introduce = introduce;
	}
	private Integer id;
	private String name;
	private Double price;
	private String introduce;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Express [id=" + id + ", name=" + name + ", price=" + price + ", introduce=" + introduce + "]";
	}

}
