package com.zioer.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Paiche implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String user_id;
	private int persons;
	private String phone;
	private String startposition;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startdatetime;
	private String endposition;
	private String driver;
	private String car;
	private String bzhu;
	private Date createdatetime;
	private int firstResult;
	private int maxResults;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStartposition() {
		return startposition;
	}
	public void setStartposition(String startposition) {
		this.startposition = startposition;
	}
	public Date getStartdatetime() {
		return startdatetime;
	}
	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}
	public String getEndposition() {
		return endposition;
	}
	public void setEndposition(String endposition) {
		this.endposition = endposition;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getBzhu() {
		return bzhu;
	}
	public void setBzhu(String bzhu) {
		this.bzhu = bzhu;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
