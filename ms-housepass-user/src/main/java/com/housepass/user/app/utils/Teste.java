package com.housepass.user.app.utils;

import java.time.LocalTime;

public class Teste {

	public static void main(String[] args) {
		
		try {
			String hour = "32:00";
			LocalTime hourFmt = LocalTime.parse(hour);
			int i = 1;
		}
		catch(Exception e) {
			System.out.println("Message error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
