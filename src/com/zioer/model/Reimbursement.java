package com.zioer.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reimbursement implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String userId;
	private double fee;
	private String note;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feedate;
	private String type;
	private String bmyj;
	private double refee;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getFeedate() {
		return feedate;
	}
	public void setFeedate(Date feedate) {
		this.feedate = feedate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBmyj() {
		return bmyj;
	}
	public void setBmyj(String bmyj) {
		this.bmyj = bmyj;
	}
	public double getRefee() {
		return refee;
	}
	public void setRefee(double refee) {
		this.refee = refee;
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


	
}
