package com.brevitaz.model;

import java.util.Date;

public class Experience {
	
	private Date startDate;
	private Date endDate;
	private String organization;
	private String job;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "Experience [startDate=" + startDate + ", endDate=" + endDate + ", organization=" + organization
				+ ", job=" + job + "]";
	}
	
	

}
