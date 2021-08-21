package com.xloud.pegasus.service.domain.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

	private Long userId;
	private String userName;
	private String mailAddress;
	private String password;
	private Date registTime;
	private Date updateTime;
	private Integer deleted;

}
