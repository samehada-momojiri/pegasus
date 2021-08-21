package com.xloud.pegasus.service.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.xloud.pegasus.service.common.utils.JsonUtils;
import com.xloud.pegasus.service.domain.repository.UserRepository;
import com.xloud.pegasus.service.domain.repository.model.User;

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
