package com.example.myfridge.domain;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;



@Entity

public class Item {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	private String name;
	private double liters;
	private int kpl;
	private String comment;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int eday;
	private int emonth;
	private int eyear;
	private String emessage;
	private String message;
	private String quan;
	public Item () {
		
	
		
		
	
		
	}
   public Item (String name, double liters, int kpl,String comment,LocalDate date) {
	   
	   this.name = name;
	   this.liters = liters;
	   this.kpl = kpl;
	   this.comment = comment;
	   this.date = date;
	   
	   
   }
   

   
   

public int getEmonth() {
	return emonth;
}
public void setEmonth(int emonth) {
	this.emonth = emonth;
}
public int getEyear() {
	return eyear;
}
public void setEyear(int eyear) {
	this.eyear = eyear;
}
public String getEmessage() {
	return emessage;
}
public void setEmessage(String emessage) {
	this.emessage = emessage;
}
public String getQuan() {
	return quan;
}
public void setQuan(String quan) {
	this.quan = quan;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getEday() {
	return eday;
}
public void setEday(int eday) {
	this.eday = eday;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getLiters() {
	return liters;
}

public void setLiters(double liters) {
	this.liters = liters;
}

public int getKpl() {
	return kpl;
}

public void setKpl(int kpl) {
	this.kpl = kpl;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}
	
   
   
   
   
}
