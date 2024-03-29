package com.appointment.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.management.dto.ErrorResponseDto;
import com.appointment.management.dto.ListResponseDto;
import com.appointment.management.dto.PaginationResponse;
import com.appointment.management.dto.RoleDto;
import com.appointment.management.dto.SuccessResponseDto;
import com.appointment.management.exceptions.ResourceNotFoundException;
import com.appointment.management.iListDto.IListRoleDto;
import com.appointment.management.iListDto.IListRolePermissionDto;
import com.appointment.management.serviceIntf.RoleServiceInterface;
import com.appointment.management.utils.Constant;
import com.appointment.management.utils.ErrorKeyConstant;
import com.appointment.management.utils.ErrorMessageConstant;
import com.appointment.management.utils.GlobalFunctions;
import com.appointment.management.utils.SuccessKeyConstant;
import com.appointment.management.utils.SuccessMessageConstant;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
@SecurityRequirement(name = "jwt")
public class RoleController {

	@Autowired
	private RoleServiceInterface roleServiceInterface;

	@PreAuthorize("hasRole('AddRole')")
	@PostMapping
	public ResponseEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {

		try {
			this.roleServiceInterface.role(roleDto);
			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.ROLE_ADDED, SuccessKeyConstant.ROLE_M032204, roleDto),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorKeyConstant.PERMISSION_E032301),
					HttpStatus.BAD_REQUEST);
		}

		catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.ROLE_ALREADY_PRESENT, ErrorKeyConstant.ROLE_E032203),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('UpdateRole')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Long id,
			@RequestAttribute(GlobalFunctions.CUSTUM_ATTRIBUTE_USER_ID) Long loggedId,
			@Valid @RequestBody RoleDto roledto) {
		try {

			this.roleServiceInterface.updateRole(roledto, loggedId, id);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.ROLE_UPDATED,
					SuccessKeyConstant.ROLE_M032203, roledto), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorKeyConstant.PERMISSION_E032301),
					HttpStatus.BAD_REQUEST);
		}

		catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.ROLE_NOT_FOUND, ErrorKeyConstant.ROLE_E032201),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('RoleList')")
	@GetMapping("/all")
	public ResponseEntity<?> allRoles(
			@RequestParam(defaultValue = Constant.DEFAULT_PAGENUMBER, value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = Constant.DEFAULT_PAGESIZE, value = Constant.PAGESIZE) String pageSize) {

		try {

			Page<IListRoleDto> roles = this.roleServiceInterface.getAllRoles(pageNo, pageSize);

			PaginationResponse response = new PaginationResponse();
			response.setPageSize(roles.getSize());
			response.setTotal(roles.getTotalElements());
			response.setPageNumber(roles.getNumber() + 1);

			return new ResponseEntity<>(new ListResponseDto(roles.getContent(), response), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.ROLE_NOT_FOUND, ErrorKeyConstant.ROLE_E032201),
					HttpStatus.BAD_REQUEST);

		}
	}

	@PreAuthorize("hasRole('RolePermission')")
	@GetMapping("/permissions/{id}")
	public ResponseEntity<?> permissionsByRole(@PathVariable(value = "id") Long id) {
		try {
			List<IListRolePermissionDto> permissions = this.roleServiceInterface.getPermissionsByRole(id);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.FETCH_USER_ROLE_PERMISSION,
					SuccessKeyConstant.ROLE_PERMISSION_M032401, permissions), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.ROLE_NOT_FOUND, ErrorKeyConstant.ROLE_E032201),
					HttpStatus.BAD_REQUEST);
		}

	}

}
