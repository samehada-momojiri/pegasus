package com.xloud.pegasus.common.bl;

import java.util.List;

import com.xloud.pegasus.common.exception.BusinessException;

public interface DataLinkageBL {

	void publish(String nameSpace, String interfaceName, String identifier, Object obj) throws BusinessException;

	<T> List<T> subscription(String nameSpace, String interfaceName, String identifier, Class<T> claszz)
			throws BusinessException;
}
