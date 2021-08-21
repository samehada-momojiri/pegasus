package com.xloud.pegasus.service.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xloud.pegasus.common.domain.repository.UserRepository;
import com.xloud.pegasus.common.domain.repository.model.User;
import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		return userRepository.findAll().stream().map((user) -> {
			return convert(user);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findById(Long id) {
		User user = userRepository.findById(id);
		if (user == null) {
			return null;
		}
		return convert(user);
	}

	@Override
	@Transactional(readOnly = false)
	public UserDto create(UserDto dto) {
		final User user = User.builder().mailAddress(dto.getMailAddress()).userName(dto.getUserName())
				.password(dto.getPassword()).registTime(DateUtils.getThreadDateTime())
				.updateTime(DateUtils.getThreadDateTime()).deleted(0).build();
		LOGGER.info("#user : {}", user);
		userRepository.insert(user);
		return convert(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(UserDto dto) {
		final User user = User.builder().id(dto.getUserId()).mailAddress(dto.getMailAddress())
				.userName(dto.getUserName()).password(dto.getPassword()).updateTime(DateUtils.getThreadDateTime())
				.deleted(dto.getDeleted()).build();
		userRepository.update(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		userRepository.delete(id);
	}

	private UserDto convert(User user) {
		UserDto dto = new UserDto();
		dto.setUserId(user.getId());
		dto.setMailAddress(user.getMailAddress());
		dto.setUserName(user.getUserName());
		dto.setRegistTime(user.getRegistTime());
		dto.setUpdateTime(user.getUpdateTime());
		dto.setDeleted(user.getDeleted());
		return dto;
	}
}
