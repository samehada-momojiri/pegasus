package com.xloud.pegasus.datalinkage.domain.service.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEnvelopeDto {

	private List<ValueObjectDto> valueObjectDtoList = Lists.newArrayList();

}
