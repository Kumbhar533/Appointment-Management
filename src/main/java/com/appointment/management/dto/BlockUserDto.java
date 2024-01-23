package com.appointment.management.dto;

import jakarta.validation.constraints.Size;

public class BlockUserDto {

	private Long blockedEntityId;

	@Size(max = 250, message = "Reson should not exceed 250 characters* invalid length")
	private String reason;

	public BlockUserDto(Long blockedEntityId, String reason) {
		super();
		this.blockedEntityId = blockedEntityId;
		this.reason = reason;
	}

	public Long getBlockedEntityId() {
		return blockedEntityId;
	}

	public void setBlockedEntityId(Long blockedEntityId) {
		this.blockedEntityId = blockedEntityId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BlockUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
