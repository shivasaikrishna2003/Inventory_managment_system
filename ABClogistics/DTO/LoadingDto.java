package com.alpha.ABClogistics.DTO;

public class LoadingDto {

	private String date;
	private String time;
	public LoadingDto(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}
	public LoadingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
