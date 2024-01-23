package com.appointment.management.dto;

import java.util.ArrayList;

import com.appointment.management.entities.RoleStatus;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {

	@Size(max = 100, message = "Name length should be exceed 100*invalid length")
	@Pattern(regexp = "[a-zA-Z]+", message = "Only alphabetical characters allowed*invalid name")
	private String userName;

	private String gender;

	private ArrayList<RoleStatus> roleId;
	@Size(max = 250, message = "Address length should be exceed 250*invalid length")
	private String address;

	public UpdateUserDto(String userName, String gender, ArrayList<RoleStatus> roleId, String address) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.roleId = roleId;
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<RoleStatus> getRoleId() {
		return roleId;
	}

	public void setRoleId(ArrayList<RoleStatus> roleId) {
		this.roleId = roleId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UpdateUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
