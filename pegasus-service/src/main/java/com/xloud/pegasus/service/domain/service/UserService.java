package com.xloud.pegasus.service.domain.service;

import java.util.List;

import com.xloud.pegasus.service.domain.service.dto.UserDto;

public interface UserService {

	List<UserDto> findAll();

	UserDto findById(Long id);

	UserDto create(UserDto dto);

	void update(UserDto dto);

	void delete(Long id);

}