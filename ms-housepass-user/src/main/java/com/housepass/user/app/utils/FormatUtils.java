package com.housepass.user.app.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {			
	
	private static final String FORMAT_DATA = "yyyy-MM-dd";
	
	private static final String FORMAT_HORA = "HH:mm";
	
	
	public static boolean isValidEmail(String email) {
	    boolean isEmailIdValid = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            isEmailIdValid = true;
	        }
	    }
	    return isEmailIdValid;
	}
	
	
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
