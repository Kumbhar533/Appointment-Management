package com.appointment.management.dto;

import java.util.ArrayList;

import jakarta.validation.constraints.Size;

public class RoleDto {

	@Size(max = 50, message = "Role name sould not exceed 50 characters* inavlid role name length")
	private String roleName;
	@Size(max = 250, message = "Discription sould not exceed 250 characters* inavlid length")
	private String description;

	private ArrayList<Long> permissions;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Long> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Long> permissions) {
		this.permissions = permissions;
	}

	public RoleDto(String roleName, String description, ArrayList<Long> permissions) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.permissions = permissions;
	}

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
