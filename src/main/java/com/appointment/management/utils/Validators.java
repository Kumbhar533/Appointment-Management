package com.appointment.management.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern EMAIL = Pattern.compile(EMAIL_PATTERN);

	public static boolean isValidforEmail(final String email) {
		Matcher matcher = EMAIL.matcher(email);
		return matcher.matches();
	}

	private static final String EMPLOYEE_NAME_PATTERN = "^(?=.{1,100}$)[a-zA-Z\\s\\d.]+$";
	private static final Pattern EMPLOYEE_NAME = Pattern.compile(EMPLOYEE_NAME_PATTERN);

	public static boolean isValidforEmployeeName(final String name) {

		Matcher matcher = EMPLOYEE_NAME.matcher(name);
		return matcher.matches();
	}

	private static final String NAME_PATTERN = "^[a-zA-Z\\s\\d]{1,100}$";

	private static final Pattern NAME = Pattern.compile(NAME_PATTERN);

	public static boolean isValidforName(final String name) {

		Matcher matcher = NAME.matcher(name);
		return matcher.matches();
	}

	private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\s]{1,250}$";
	private static final Pattern ADDRESS = Pattern.compile(ADDRESS_PATTERN);

	public static boolean isValidForAddress(final String address) {
		Matcher matcher = ADDRESS.matcher(address);
		return matcher.matches();
	}
}
