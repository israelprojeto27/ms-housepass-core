package com.housepass.user.app.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {			
	
	private static final String FORMAT_DATA = "yyyy-MM-dd";
	
	private static final String FORMAT_HORA = "HH:mm";
	
	
	public static LocalDate converteStringToLocalDateTime(String data) {
		
		try {			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATA);
			return LocalDate.parse(data, formatter);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static LocalTime converteStringToLocalTime(String hora) {
		
		try {	
			return LocalTime.parse(hora);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String converteLocalDateTimeToString(LocalDateTime dataHora) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return dataHora.format(formatter);
		}
		catch(Exception e) {
			return "";
		}
	}
	
	public static String converteLocalDateToString(LocalDate dataHora) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return dataHora.format(formatter);
		}
		catch(Exception e) {
			return "";
		}
	}
	
	public static String converteLocalTimeToString(LocalTime hora) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			return hora.format(formatter);
		}
		catch(Exception e) {
			return "";
		}
	}

}
