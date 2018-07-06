package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SimpleTimeClient implements TimeClient{
	private LocalDateTime localDateTime;
	
	public SimpleTimeClient() {
		localDateTime = LocalDateTime.now();
	}

	@Override
	public void setTime(int hour, int minute, int second) {
		LocalDate date = LocalDate.from(localDateTime);
		//localDateTime.of(date, time)
		
	}

	@Override
	public void setDate(int day, int month, int year) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
