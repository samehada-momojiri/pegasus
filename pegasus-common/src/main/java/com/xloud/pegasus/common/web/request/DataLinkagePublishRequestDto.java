package com.xloud.pegasus.common.web.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataLinkagePublishRequestDto extends DataLinkageSubscriptionRequestDto {

	private String type;
	private String data;

}
