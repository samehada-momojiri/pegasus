package com.xloud.pegasus.common.domain.repository.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long id;
	private String userName;
	private String mailAddress;
	private String password;
	private Date registTime;
	private Date updateTime;
	private Integer deleted;
}
