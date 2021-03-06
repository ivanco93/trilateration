package com.icastiblanco.trilateration.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Satellite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@Column(nullable=false)
	private double x;
	@Column(nullable=false)
	private double y;
	private double distance;
	private double originalX;
	private double originalY;
	private String[] message;
	
	//for deserialisation
	public Satellite() {
		this.originalX = this.x;
		this.originalY = y;
	}

	public Satellite(long id, String name, double x, double y, double distance, double originalX, double originalY,
			String[] message) {
		super();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.originalX = originalX;
		this.originalY = originalY;
		this.message = message;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getOriginalX() {
		return originalX;
	}
	public void setOriginalX(double originalX) {
		this.originalX = originalX;
	}
	public double getOriginalY() {
		return originalY;
	}
	public void setOriginalY(double originalY) {
		this.originalY = originalY;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Satellite [id=" + id + ", name=" + name + ", x=" + x + ", y=" + y + ", distance=" + distance
				+ ", originalX=" + originalX + ", originalY=" + originalY + ", message=" + Arrays.toString(message)
				+ "]";
	}
}
