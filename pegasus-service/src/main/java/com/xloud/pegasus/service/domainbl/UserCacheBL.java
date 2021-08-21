package com.xloud.pegasus.service.domainbl;

import com.xloud.pegasus.common.domain.repository.model.User;

public interface UserCacheBL {
	User findById(Long userId);
}
