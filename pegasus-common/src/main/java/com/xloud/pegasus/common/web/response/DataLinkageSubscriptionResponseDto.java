package com.xloud.pegasus.common.web.response;

import java.util.List;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataLinkageSubscriptionResponseDto extends ApiResponseBaseDto {

	private String nameSpace;
	private String interfaceName;
	private String identifier;
	private List<ValueObjectDto> valueObjectDtoList = Lists.newArrayList();

}
