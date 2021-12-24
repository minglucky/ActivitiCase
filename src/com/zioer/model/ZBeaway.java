package com.zioer.model;

import java.io.Serializable;
import java.util.Date;

public class ZBeaway implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
    private String pid;
    private String paiche_id;
    private String user_id;
    private String username;
    private String sort;
    private String codename;
    private Date startdatetime;
    private Date enddatetime;
    private String phone;
    private String onposition;
    private Double borrowmoney;
    private String bzhu;
    private Date createdatetime;
    
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
	public String getPaiche_id() {
		return paiche_id;
	}
	public void setPaiche_id(String paiche_id) {
		this.paiche_id = paiche_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public Date getStartdatetime() {
		return startdatetime;
	}
	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}
	public Date getEnddatetime() {
		return enddatetime;
	}
	public void setEnddatetime(Date enddatetime) {
		this.enddatetime = enddatetime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOnposition() {
		return onposition;
	}
	public void setOnposition(String onposition) {
		this.onposition = onposition;
	}
	public Double getBorrowmoney() {
		return borrowmoney;
	}
	public void setBorrowmoney(Double borrowmoney) {
		this.borrowmoney = borrowmoney;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	

}