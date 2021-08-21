package com.xloud.pegasus.service.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.xloud.pegasus.common.domain.repository.UserRepository;
import com.xloud.pegasus.common.domain.repository.model.User;
import com.xloud.pegasus.common.utils.JsonUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserCacheObject {

	private final UserRepository userRepository;

	@Cacheable(cacheNames = "pegasus-cache", key = "#key")
	public String findByUserId(Long userId, String key) {
		User user = userRepository.findById(userId);

		return JsonUtils.convertToJson(user);
	}
}
