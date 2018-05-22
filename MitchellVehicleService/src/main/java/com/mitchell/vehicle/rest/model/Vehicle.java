package com.mitchell.vehicle.rest.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle {
	
	@XmlElement(name="id")
	@NotNull(message = "Id cannot be empty")
	private Integer id;
	@XmlElement(name="year")
	@Min(1950)
	@Max(2050)
	private long year;
	@XmlElement(name="make")
	@NotNull(message = "Make should not be left blank")
	private String make;
	@XmlElement(name="model")
	@NotNull(message = "Model should not be left blank")
	private String model;
	
	public Vehicle() {
	}
	public Vehicle(Integer id, long year, String make, String model) {
		super();
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
	}
	
	public Vehicle(VehicleBuilder builder){
		this.id = builder.id;
		this.make = builder.make;
		this.model = builder.model;
		this.year = builder.year;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + "]";
	}
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (int) (year ^ (year >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id != other.id)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (year != other.year)
			return false;
		return true;
	}*/


	//Vehicle Builder class to make Vehicle class immutable when using as a key to HashMap
	public static final class VehicleBuilder{
		private Integer id;
		private String make;
		private String model;
		private long year;
		
		private VehicleBuilder(){
			
		}
		
		public static VehicleBuilder getVehicleInstance(){
			return new VehicleBuilder();
		}
		
		public static VehicleBuilder getVehicleInstance(Vehicle v){
			return getVehicleInstance().withId(v.getId()).withMake(v.getMake()).withModel(v.getModel()).withYear(v.getYear());
		}
		
		public VehicleBuilder withId(Integer id){
			this.id = id;
			return this;
		}
		
		public VehicleBuilder withMake(String make){
			this.make = make;
			return this;
		}
		
		public VehicleBuilder withModel(String model){
			this.model = model;
			return this;
		}
		
		public VehicleBuilder withYear(long year){
			this.year = year;
			return this;
		}
		
		public Vehicle build(){
			return new Vehicle(this);
		}
	}

}
