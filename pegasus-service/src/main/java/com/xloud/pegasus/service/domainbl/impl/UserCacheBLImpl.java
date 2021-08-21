package com.xloud.pegasus.service.domainbl.impl;

import org.springframework.stereotype.Component;

import com.xloud.pegasus.service.cache.UserCacheObject;
import com.xloud.pegasus.service.common.utils.JsonUtils;
import com.xloud.pegasus.service.domain.repository.model.User;
import com.xloud.pegasus.service.domainbl.UserCacheBL;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserCacheBLImpl implements UserCacheBL {

	private final UserCacheObject userCacheObject;

	@Override
	public User findById(Long userId) {
		String json = userCacheObject.findByUserId(userId, "User::" + userId);
		return JsonUtils.convertFromJson(json, User.class);
	}

}
