package com.xloud.pegasus.datalinkage.domain.service.dto;

import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishEnvelopeDto {

	private String messageKey;
	private ValueObjectDto valueObjectDto;

}
