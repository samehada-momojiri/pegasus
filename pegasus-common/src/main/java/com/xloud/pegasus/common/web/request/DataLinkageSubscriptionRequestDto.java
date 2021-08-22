package com.xloud.pegasus.common.web.request;

import lombok.Data;

@Data
public class DataLinkageSubscriptionRequestDto {

	private String nameSpace;
	private String interfaceName;
	private String identifier;

}
